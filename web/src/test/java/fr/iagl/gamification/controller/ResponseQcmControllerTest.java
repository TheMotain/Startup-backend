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
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.model.ResultQcmModel;
import fr.iagl.gamification.services.ResultQcmService;
import fr.iagl.gamification.validator.ResultQcmForm;

public class ResponseQcmControllerTest extends SpringIntegrationTest{

	@InjectMocks
	private ResponseQcmController controller;
	
	@Mock
	private ResultQcmService service;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllResultQcmCallGetAllResponseFromService(){
		controller.getAllResultQcm(2L);
		Mockito.verify(service, Mockito.times(1)).getAllQcmResultsByIdQcm(2L);
	}
	
	@Test
	public void testGetAllResultQcmReturnResponseEntityContainsServiceResultMultiple(){
		ResultQcmModel mock = Mockito.mock(ResultQcmModel.class);
		List<ResultQcmModel> lst = Arrays.asList(mock);
		Mockito.when(service.getAllQcmResultsByIdQcm(2L)).thenReturn(lst);
		ResponseEntity<List<ResultQcmModel>> response = controller.getAllResultQcm(2L);
		Assert.assertEquals(mock, response.getBody().get(0));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	

	@Test
	public void testGetAllResultQcmReturnResponseEntityContainsServiceResult(){
		List<ResultQcmModel> mock = new ArrayList<>();
		Mockito.when(service.getAllQcmResultsByIdQcm(2L)).thenReturn(mock);
		ResponseEntity<List<ResultQcmModel>> response = controller.getAllResultQcm(2L);
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testResultQcmFormOK() throws GamificationServiceException{
		ResultQcmForm resultQcmForm = Mockito.mock(ResultQcmForm.class);
		ResultQcmModel resultQcmModel = Mockito.mock(ResultQcmModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(2L)).when(resultQcmForm).getIdAnswer();
		Mockito.doReturn(1L).when(resultQcmForm).getIdStudent();
		Mockito.doReturn(Arrays.asList(resultQcmModel)).when(service).saveResultQcm(Mockito.anyList(), Mockito.anyLong());
		ResponseEntity output = controller.submitQcmForm(resultQcmForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).saveResultQcm(Mockito.anyList(), Mockito.anyLong());
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testResultQcmFormKOServiceReturnNull() throws GamificationServiceException{
		ResultQcmForm qcmForm = Mockito.mock(ResultQcmForm.class);
		ResultQcmModel qcmModel = Mockito.mock(ResultQcmModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(2L).when(qcmForm).getIdStudent();
		Mockito.doReturn(Arrays.asList(2L)).when(qcmForm).getIdAnswer();
		Mockito.doReturn(null).when(service).saveResultQcm(Mockito.anyList(), Mockito.anyLong());
		
		ResponseEntity output = controller.submitQcmForm(qcmForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).saveResultQcm(Mockito.anyList(), Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}

	@Test
	public void testResultQcmFormKO() throws GamificationServiceException{
		ResultQcmForm resultQcmForm = Mockito.mock(ResultQcmForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		ResponseEntity output = controller.submitQcmForm(resultQcmForm, bindingResult);
		Mockito.verify(service, Mockito.never()).saveResultQcm(Mockito.anyList(), Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testQcmFormKOErrorService() throws GamificationServiceException{
		ResultQcmForm qcmForm = Mockito.mock(ResultQcmForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		QcmModel qcm = Mockito.mock(QcmModel.class);
		GamificationServiceException exception = Mockito.mock(GamificationServiceException.class);
		Mockito.doReturn(Arrays.asList(CodeError.ERROR_NOT_EXISTS_STUDENT)).when(exception).getErrors();
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(2L).when(qcmForm).getIdStudent();
		Mockito.doReturn(Arrays.asList(1L)).when(qcmForm).getIdAnswer();
		Mockito.doThrow(exception).when(service).saveResultQcm(Mockito.anyList(), Mockito.anyLong());
		
		ResponseEntity output = controller.submitQcmForm(qcmForm, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
		assertEquals("["+CodeError.ERROR_NOT_EXISTS_STUDENT+"]", output.getBody().toString());
	}
}
