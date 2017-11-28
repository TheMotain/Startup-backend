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
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.model.StudentModel;
import fr.iagl.gamification.services.PointService;
import fr.iagl.gamification.validator.PointForm;

public class PointControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private PointController controller;
	
	@Mock
	private PointService pointService;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllPointCallGetAllFromService(){
		controller.getAllPoints();
		Mockito.verify(pointService, Mockito.times(1)).getPoints();
	}
	
	@Test
	public void testGetAllPointReturnResponseEntityContainsServiceResultMultiple(){
		PointModel mock = Mockito.mock(PointModel.class);
		PointForm cls = Mockito.mock(PointForm.class);
		Mockito.when(mapper.map(mock, PointForm.class)).thenReturn(cls);
		List<PointModel> lst = Arrays.asList(mock);
		Mockito.when(pointService.getPoints()).thenReturn(lst);
		ResponseEntity<List<PointForm>> response = controller.getAllPoints();
		Assert.assertEquals(cls, response.getBody().get(0));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetAllPointReturnResponseEntityContainsServiceResult(){
		List<PointModel> mock = new ArrayList<>();
		Mockito.when(pointService.getPoints()).thenReturn(mock);
		ResponseEntity<List<PointForm>> response = controller.getAllPoints();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testPointFormOK() throws GamificationServiceException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		PointModel classModel = Mockito.mock(PointModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(classModel).when(pointService).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());

		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		Mockito.verify(pointService, Mockito.times(1)).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOServiceReturnNull() throws GamificationServiceException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		PointModel pointModel = Mockito.mock(PointModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(2L).when(pointForm).getIdStudent();
		Mockito.doReturn(pointModel).when(mapper).map(pointForm, PointModel.class);
		
		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		Mockito.verify(pointService, Mockito.times(1)).updatePoint(pointModel, 2L);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}

	@Test
	public void testClassFormKO() throws GamificationServiceException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		Mockito.verify(pointService, Mockito.never()).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testClassFormKOStudentNotExisted() throws GamificationServiceException{
		PointForm pointForm = Mockito.mock(PointForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		PointModel point = Mockito.mock(PointModel.class);
		GamificationServiceException exception = Mockito.mock(GamificationServiceException.class);
		Mockito.doReturn(Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT)).when(exception).getErrors();
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(point).when(mapper).map(pointForm, PointModel.class);
		Mockito.doThrow(exception).when(pointService).updatePoint(Mockito.any(PointModel.class), Mockito.anyLong());
		
		ResponseEntity output = controller.submitPointForm(pointForm, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
		assertEquals("["+CodeError.ERROR_NOT_EXISTS_STUDENT+"]", output.getBody().toString());
	}
	
	@Test
	public void testGetPointCallService() throws GamificationServiceException {
		controller.getPoint(Mockito.anyLong());
		Mockito.verify(pointService, Mockito.times(1)).getPoint(Mockito.anyLong());
	}
	
	@Test
	public void testReturnErrorListStringIfUserNotKnow() throws GamificationServiceException {
		Mockito.when(pointService.getPoint(Mockito.anyLong())).thenThrow(new GamificationServiceException(null));
		ResponseEntity<List<String>> res = (ResponseEntity<List<String>>) controller.getPoint(Mockito.anyLong());
		Assert.assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}
	
	@Test
	public void testReturnMappedPointFormFromReturnServiceAndUserID() throws GamificationServiceException {
		PointModel model = Mockito.mock(PointModel.class);
		PointForm form = Mockito.mock(PointForm.class);
		Mockito.when(pointService.getPoint(Mockito.anyLong())).thenReturn(model);
		Mockito.when(mapper.map(model, PointForm.class)).thenReturn(form);
		ResponseEntity<PointForm> res = (ResponseEntity<PointForm>) controller.getPoint(Mockito.anyLong());
		Assert.assertEquals(form, res.getBody());
		Assert.assertEquals(HttpStatus.OK, res.getStatusCode());
		Mockito.verify(mapper, Mockito.times(1)).map(Mockito.any(), Mockito.any());
	}
}
