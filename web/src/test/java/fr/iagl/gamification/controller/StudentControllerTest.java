package fr.iagl.gamification.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.services.StudentService;
import fr.iagl.gamification.validator.StudentForm;

public class StudentControllerTest extends SpringIntegrationTest {
	
	@InjectMocks
	private StudentController controller;
	
	@Mock
	private StudentService studentService;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllStudentCallGetAllFromService() throws GamificationServiceException{
		controller.getAllStudent();
		Mockito.verify(studentService, Mockito.times(1)).getAllStudent();
	}
	
	@Test
	public void testGetAllStudentReturnResponseEntityContainsServiceResult(){
		List<StudentModel> mock = new ArrayList<>();
		Mockito.when(studentService.getAllStudent()).thenReturn(mock);
		ResponseEntity<List<StudentForm>> response = controller.getAllStudent();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetAllStudentReturnResponseEntityContainsServiceResultMultiple(){
		StudentModel mock = Mockito.mock(StudentModel.class);
		List<StudentModel> lst = Arrays.asList(mock);
		StudentForm obj = Mockito.mock(StudentForm.class);
		Mockito.when(mapper.map(mock, StudentForm.class)).thenReturn(obj);
		Mockito.when(studentService.getAllStudent()).thenReturn(lst);
		ResponseEntity<List<StudentForm>> response = controller.getAllStudent();
		Assert.assertEquals(obj, response.getBody().get(0));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testCreateStudentCallCreateFromService() throws GamificationServiceException{
		controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Mockito.verify(studentService, Mockito.times(1)).saveStudent((StudentModel)Mockito.any());
	}
	
	@Test
	public void testCreateStudentCallCreateFromServiceWithMappedModel() throws GamificationServiceException{
		StudentModel model = Mockito.mock(StudentModel.class);
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(model);
		ArgumentCaptor<StudentModel> modelCaptor = ArgumentCaptor.forClass(StudentModel.class);

		controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Mockito.verify(mapper, Mockito.times(1)).map((StudentForm)Mockito.any(), Mockito.eq(StudentModel.class));
		Mockito.verify(studentService, Mockito.times(1)).saveStudent(modelCaptor.capture());
		Assert.assertEquals(model, modelCaptor.getValue());
	}
	
	@Test
	public void testCreateStudentReturnNewCreatedStudent() throws GamificationServiceException{
		StudentModel in = Mockito.mock(StudentModel.class);
		StudentForm out = Mockito.mock(StudentForm.class);
		Mockito.when(mapper.map((StudentModel)Mockito.any(), Mockito.eq(StudentModel.class))).thenReturn(in);
		Mockito.when(studentService.saveStudent(in)).thenReturn(in);
		Mockito.when(mapper.map(in, StudentForm.class)).thenReturn(out);
		ResponseEntity<StudentModel> response = (ResponseEntity<StudentModel>) controller.createStudent(Mockito.mock(StudentForm.class), Mockito.mock(BindingResult.class));
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assert.assertEquals(out, response.getBody());
	}
	
	@Test
	public void testCreateStudentErrorOnFormReturnKoResponse(){
		StudentForm form = Mockito.mock(StudentForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		ResponseEntity<List<String>> response = (ResponseEntity<List<String>>) controller.createStudent(form, bindingResult);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void testCreateStudentErrorOnFormResponseContainsErrors(){
		StudentForm form = Mockito.mock(StudentForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError errors = Mockito.mock(ObjectError.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn("message").when(errors).getDefaultMessage();
		Mockito.doReturn(Arrays.asList(errors)).when(bindingResult).getAllErrors();
		ResponseEntity<List<String>> response = (ResponseEntity<List<String>>) controller.createStudent(form, bindingResult);
		Assert.assertEquals("message", response.getBody().get(0));
	}
	
	@Test
	public void testCreateStudentErrorOnServiceReturnResponseContainsErrors() throws GamificationServiceException{
		StudentForm form = Mockito.mock(StudentForm.class);
		StudentModel model = Mockito.mock(StudentModel.class);
		GamificationServiceException exception = Mockito.mock(GamificationServiceException.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(model).when(mapper).map(form, StudentModel.class);
		Mockito.doThrow(exception).when(studentService).saveStudent(Mockito.any(StudentModel.class));
		Mockito.doReturn(Arrays.asList("ERROR")).when(exception).getErrors();
		
		ResponseEntity<List<String>> output = (ResponseEntity<List<String>>) controller.createStudent(form, bindingResult);
		Assert.assertEquals("ERROR", output.getBody().get(0));
	}

}
