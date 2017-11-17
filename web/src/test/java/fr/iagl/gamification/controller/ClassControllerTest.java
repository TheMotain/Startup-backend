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
import fr.iagl.gamification.exceptions.ClassroomExistsException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.model.StudentModel;
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
	public void testClassFormOK() throws ClassroomExistsException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		ClassModel classModel = Mockito.mock(ClassModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(classModel).when(service).createClass(Mockito.any(ClassModel.class));

		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).createClass(Mockito.any());
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}

	@Test
	public void testClassFormKO() throws ClassroomExistsException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		Mockito.verify(service, Mockito.never()).createClass(classe);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOClassAlreadyExisted() throws ClassroomExistsException{
		ClassForm classForm = Mockito.mock(ClassForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		ClassModel classe = Mockito.mock(ClassModel.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(classe).when(mapper).map(classForm, ClassModel.class);
		Mockito.doThrow(ClassroomExistsException.class).when(service).createClass(Mockito.any());
		
		ResponseEntity output = controller.submitClassForm(classForm, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
		assertEquals("["+CodeError.ERROR_EXISTS_CLASS+"]", output.getBody().toString());
	}
	
	@Test
	public void testGetAllClassroomCallGetAllFromService() throws ClassroomExistsException{
		controller.getAllClassroom();
		Mockito.verify(service, Mockito.times(1)).getAllClassroom();
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
