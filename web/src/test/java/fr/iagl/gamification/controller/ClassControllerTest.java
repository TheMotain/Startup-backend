package fr.iagl.gamification.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.services.ClassService;
import fr.iagl.gamification.validator.ClassForm;

public class ClassControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private ClassController controller;
	
	@Mock
	private ClassService service;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testClassFormOK() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		ClassModel classModel = Mockito.mock(ClassModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		//TODO
		Mockito.doReturn(classModel).when(service).createClass(Mockito.any(ClassModel.class),0L);

		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		//TODO
		Mockito.verify(service, Mockito.times(1)).createClass(Mockito.any(),0L);
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOServiceReturnNull() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		ClassModel classModel = Mockito.mock(ClassModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();

		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		//TODO
		Mockito.verify(service, Mockito.times(1)).createClass(Mockito.any(),0L);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}

	@Test
	public void testClassFormKO() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		//TODO
		Mockito.verify(service, Mockito.never()).createClass(classe,0L);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOClassAlreadyExisted() throws GamificationServiceException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		GamificationServiceException exception = Mockito.mock(GamificationServiceException.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(classe).when(mapper).map(classForm, ClassModel.class);
		//TODO
		Mockito.doThrow(exception).when(service).createClass(Mockito.any(),0L);
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
