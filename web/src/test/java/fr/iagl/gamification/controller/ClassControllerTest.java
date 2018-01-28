package fr.iagl.gamification.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.model.TeacherModel;
import fr.iagl.gamification.services.ClassService;
import fr.iagl.gamification.services.TeacherService;
import fr.iagl.gamification.validator.ClassForm;

public class ClassControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private ClassController controller;
	
	@Mock
	private ClassService service;
	
	@Mock
	private Mapper mapper;
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Mock
	protected TeacherService teacherService;

	 @Before
     public void onSetUp() {
		 
		MockitoAnnotations.initMocks(this);

		 this.mockMvc = MockMvcBuilders
                 .webAppContextSetup(this.wac)
                 .build();
         Authentication auth = new UsernamePasswordAuthenticationToken(
			      "string@s.com", "string", Arrays.asList(new SimpleGrantedAuthority("ROLE_TEACHER")));
         
         SecurityContextHolder.getContext().setAuthentication(auth);
         MockitoAnnotations.initMocks(this);
     }

	 @After
	 public void tearDown() {
		 SecurityContextHolder.clearContext();
	 }

	
	@Test
	public void testClassFormOK() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		ClassModel classModel = Mockito.mock(ClassModel.class);
		
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		
		Mockito.doReturn(teacher).when(teacherService).getTeacherByMail("string@s.com");
		
		Mockito.doReturn(2L).when(teacher).getId();
		Mockito.doReturn(classModel).when(service).createClass(Mockito.any(ClassModel.class),Mockito.anyLong());
		
		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).createClass(Mockito.any(),Mockito.anyLong());
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOServiceReturnNull() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		ClassModel classModel = Mockito.mock(ClassModel.class);
		
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		
		Mockito.doReturn(teacher).when(teacherService).getTeacherByMail("string@s.com");
		Mockito.doReturn(2L).when(teacher).getId();
		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).createClass(Mockito.any(),Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}

	@Test
	public void testClassFormKO() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		Mockito.doReturn(teacher).when(teacherService).getTeacherByMail("string@s.com");
		Mockito.doReturn(2L).when(teacher).getId();
		
		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		Mockito.verify(service, Mockito.never()).createClass(Mockito.any(ClassModel.class),Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOClassAlreadyExisted() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		TeacherModel teacher = Mockito.mock(TeacherModel.class);

		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		GamificationServiceException exception = Mockito.mock(GamificationServiceException.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(classe).when(mapper).map(classForm, ClassModel.class);
		Mockito.doReturn(teacher).when(teacherService).getTeacherByMail("string@s.com");
		Mockito.doReturn(2L).when(teacher).getId();
		Mockito.doThrow(exception).when(service).createClass(Mockito.any(),Mockito.anyLong());
		Mockito.doReturn(Arrays.asList(CodeError.ERROR_EXISTS_CLASS)).when(exception).getErrors();
		
		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
		assertEquals("["+CodeError.ERROR_EXISTS_CLASS+"]", output.getBody().toString());
	}
	
	@Test
	public void testGetAllClassroomCallGetAllFromService() throws GamificationServiceException{
		controller.getAllClassroom();
		Mockito.verify(service, Mockito.times(1)).getAllClassroom();
	}
	
	@Test
	public void testGetAllClassroomReturnResponseEntityContainsServiceResultMultiple(){
		ClassModel mock = Mockito.mock(ClassModel.class);
		List<ClassModel> lst = Arrays.asList(mock);
		Mockito.when(service.getAllClassroom()).thenReturn(lst);
		ResponseEntity<List<ClassModel>> response = controller.getAllClassroom();
		Assert.assertEquals(mock, response.getBody().get(0));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetAllClassroomReturnResponseEntityContainsServiceResult(){
		List<ClassModel> mock = new ArrayList<>();
		Mockito.when(service.getAllClassroom()).thenReturn(mock);
		ResponseEntity<List<ClassModel>> response = controller.getAllClassroom();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
