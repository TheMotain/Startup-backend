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
import fr.iagl.gamification.exceptions.StudentNotFoundException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.object.PointObject;
import fr.iagl.gamification.services.PointService;
import fr.iagl.gamification.validator.ClassForm;
import fr.iagl.gamification.validator.PointForm;

public class PointControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private PointController controller;
	
	@Mock
	private PointService service;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllPointCallGetAllFromService(){
		controller.getAllPoints();
		Mockito.verify(service, Mockito.times(1)).getPoints();
	}
	
	@Test
	public void testGetAllPointReturnResponseEntityContainsServiceResultMultiple(){
		PointModel mock = Mockito.mock(PointModel.class);
		PointObject cls = Mockito.mock(PointObject.class);
		Mockito.when(mapper.map(mock, PointObject.class)).thenReturn(cls);
		List<PointModel> lst = Arrays.asList(mock);
		Mockito.when(service.getPoints()).thenReturn(lst);
		ResponseEntity<List<PointObject>> response = controller.getAllPoints();
		Assert.assertEquals(cls, response.getBody().get(0));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetAllPointReturnResponseEntityContainsServiceResult(){
		List<PointModel> mock = new ArrayList<>();
		Mockito.when(service.getPoints()).thenReturn(mock);
		ResponseEntity<List<PointObject>> response = controller.getAllPoints();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testPointFormOK() throws StudentNotFoundException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		PointModel classModel = Mockito.mock(PointModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(classModel).when(service).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());

		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOServiceReturnNull() throws StudentNotFoundException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		PointModel pointModel = Mockito.mock(PointModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(2L).when(pointForm).getIdStudent();
		Mockito.doReturn(pointModel).when(mapper).map(pointForm, PointModel.class);
		
		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).updatePoint(pointModel, 2L);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}

	@Test
	public void testClassFormKO() throws StudentNotFoundException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		Mockito.verify(service, Mockito.never()).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOStudentNotExisted() throws StudentNotFoundException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		PointModel point = Mockito.mock(PointModel.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(point).when(mapper).map(pointForm, PointModel.class);
		Mockito.doThrow(StudentNotFoundException.class).when(service).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());
		
		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
		assertEquals("["+CodeError.ERROR_NOT_EXISTS_STUDENT+"]", output.getBody().toString());
	}
}
