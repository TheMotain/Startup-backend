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
import fr.iagl.gamification.model.TeacherModel;
import fr.iagl.gamification.services.TeacherService;
import fr.iagl.gamification.validator.TeacherForm;

public class TeacherControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private TeacherController controller;
	
	@Mock
	private TeacherService service;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testTeacherFormOK() throws GamificationServiceException{
		TeacherForm teacherForm = Mockito.mock(TeacherForm.class);
		TeacherModel teacherModel = Mockito.mock(TeacherModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(teacherModel).when(service).createTeacher(Mockito.any(TeacherModel.class));

		ResponseEntity output = controller.submitTeacherForm(teacherForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).createTeacher(Mockito.any());
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testTeacherFormKOServiceReturnNull() throws GamificationServiceException{
		TeacherForm teacherForm = Mockito.mock(TeacherForm.class);
		TeacherModel teacherModel = Mockito.mock(TeacherModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();

		ResponseEntity output = controller.submitTeacherForm(teacherForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).createTeacher(Mockito.any());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}

	@Test
	public void testTeacherFormKO() throws GamificationServiceException{
		TeacherForm teacherForm = Mockito.mock(TeacherForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		ResponseEntity output = controller.submitTeacherForm(teacherForm, bindingResult);
		Mockito.verify(service, Mockito.never()).createTeacher(teacher);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testTeacherFormKOClassAlreadyExisted() throws GamificationServiceException{
		TeacherForm teacherForm = Mockito.mock(TeacherForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		TeacherModel teacher = Mockito.mock(TeacherModel.class);
		GamificationServiceException exception = Mockito.mock(GamificationServiceException.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(teacher).when(mapper).map(teacherForm, TeacherModel.class);
		Mockito.doThrow(exception).when(service).createTeacher(Mockito.any());
		Mockito.doReturn(Arrays.asList(CodeError.ERROR_EXISTS_CLASS)).when(exception).getErrors();
		
		ResponseEntity output = controller.submitTeacherForm(teacherForm, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
		assertEquals("["+CodeError.ERROR_EXISTS_CLASS+"]", output.getBody().toString());
	}
	
	@Test
	public void testGetAllTeacherCallGetAllFromService() throws GamificationServiceException{
		controller.getAllTeacher();
		Mockito.verify(service, Mockito.times(1)).getAllTeacher();
	}
	
	@Test
	public void testGetAllTeacherReturnResponseEntityContainsServiceResultMultiple(){
		TeacherModel mock = Mockito.mock(TeacherModel.class);
		List<TeacherModel> lst = Arrays.asList(mock);
		Mockito.when(service.getAllTeacher()).thenReturn(lst);
		ResponseEntity<List<TeacherModel>> response = controller.getAllTeacher();
		Assert.assertEquals(mock, response.getBody().get(0));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetAllTeacherReturnResponseEntityContainsServiceResult(){
		List<TeacherModel> mock = new ArrayList<>();
		Mockito.when(service.getAllTeacher()).thenReturn(mock);
		ResponseEntity<List<TeacherModel>> response = controller.getAllTeacher();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
