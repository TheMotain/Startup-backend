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
import fr.iagl.gamification.model.AnswerModel;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.model.QuestionModel;
import fr.iagl.gamification.object.QcmObject;
import fr.iagl.gamification.services.QcmService;
import fr.iagl.gamification.validator.AnswerForm;
import fr.iagl.gamification.validator.QcmForm;
import fr.iagl.gamification.validator.QuestionForm;

public class QcmControllerTest extends SpringIntegrationTest {

	@InjectMocks
	private QcmController controller;
	
	@Mock
	private QcmService service;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllQcmCallGetAllFromService(){
		controller.getAllQcm();
		Mockito.verify(service, Mockito.times(1)).getAllQcm();
	}
	
	@Test
	public void testGetAllQcmReturnResponseEntityContainsServiceResultMultiple(){
		QcmModel mock = Mockito.mock(QcmModel.class);
		List<QcmModel> lst = Arrays.asList(mock);
		Mockito.when(service.getAllQcm()).thenReturn(lst);
		ResponseEntity<List<QcmModel>> response = controller.getAllQcm();
		Assert.assertEquals(mock, response.getBody().get(0));
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetAllQcmReturnResponseEntityContainsServiceResult(){
		List<QcmModel> mock = new ArrayList<>();
		Mockito.when(service.getAllQcm()).thenReturn(mock);
		ResponseEntity<List<QcmModel>> response = controller.getAllQcm();
		Assert.assertEquals(mock, response.getBody());
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testQcmFormOK() throws GamificationServiceException{
		QcmForm qcmForm = Mockito.mock(QcmForm.class);
		QcmModel qcmModel = Mockito.mock(QcmModel.class);
		QuestionForm questionForm = Mockito.mock(QuestionForm.class);
		QuestionModel questionModel = Mockito.mock(QuestionModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		AnswerModel answerModel = Mockito.mock(AnswerModel.class);
		AnswerForm answerForm = Mockito.mock(AnswerForm.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(2L).when(qcmForm).getIdClass();
		Mockito.doReturn(qcmModel).when(service).saveQcm(qcmModel, 2L);
		Mockito.doReturn(qcmModel).when(mapper).map(qcmForm, QcmModel.class);
		Mockito.doReturn(Arrays.asList(questionForm)).when(qcmForm).getQuestions();
		Mockito.doReturn(questionModel).when(mapper).map(questionForm, QuestionModel.class);
		Mockito.doReturn(Arrays.asList(answerForm)).when(questionForm).getChoices();
		Mockito.doReturn(answerModel).when(mapper).map(answerForm, AnswerModel.class);
		
		ResponseEntity output = controller.submitQcmForm(qcmForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).saveQcm(Mockito.any(QcmModel.class), Mockito.anyLong());
		assertEquals(HttpStatus.OK, output.getStatusCode());
	}
	
	@Test
	public void testQcmFormKOServiceReturnNull() throws GamificationServiceException{
		QcmForm qcmForm = Mockito.mock(QcmForm.class);
		QcmModel qcmModel = Mockito.mock(QcmModel.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn(2L).when(qcmForm).getIdClass();
		Mockito.doReturn(qcmModel).when(mapper).map(qcmForm, QcmModel.class);
		
		ResponseEntity output = controller.submitQcmForm(qcmForm, bindingResult);
		Mockito.verify(service, Mockito.times(1)).saveQcm(qcmModel, 2L);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}

	@Test
	public void testQcmFormKO() throws GamificationServiceException{
		QcmForm qcmForm = Mockito.mock(QcmForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		Mockito.doReturn(true).when(bindingResult).hasErrors();
		Mockito.doReturn(Arrays.asList(error)).when(bindingResult).getAllErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		
		ResponseEntity output = controller.submitQcmForm(qcmForm, bindingResult);
		Mockito.verify(service, Mockito.never()).saveQcm(Mockito.any(QcmModel.class), Mockito.anyLong());
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
	}
	
	@Test
	public void testQcmFormKOClassNotExisted() throws GamificationServiceException{
		QcmForm qcmForm = Mockito.mock(QcmForm.class);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		ObjectError error = Mockito.mock(ObjectError.class);
		QcmModel qcm = Mockito.mock(QcmModel.class);
		GamificationServiceException exception = Mockito.mock(GamificationServiceException.class);
		Mockito.doReturn(Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM)).when(exception).getErrors();
		Mockito.doReturn(false).when(bindingResult).hasErrors();
		Mockito.doReturn("error message").when(error).getDefaultMessage();
		Mockito.doReturn(qcm).when(mapper).map(qcmForm, QcmModel.class);
		Mockito.doReturn(2L).when(qcmForm).getIdClass();
		Mockito.doThrow(exception).when(service).saveQcm(Mockito.any(QcmModel.class), Mockito.anyLong());
		
		ResponseEntity output = controller.submitQcmForm(qcmForm, bindingResult);
		assertEquals(HttpStatus.BAD_REQUEST, output.getStatusCode());
		assertEquals("["+CodeError.ERROR_NOT_EXISTS_CLASSROOM+"]", output.getBody().toString());
	}
	
}
