package fr.iagl.gamification.services.impl;

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

import fr.iagl.gamification.entity.AnswerEntity;
import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.entity.QcmEntity;
import fr.iagl.gamification.entity.QuestionEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AnswerModel;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.model.QuestionModel;
import fr.iagl.gamification.repository.ClassRepository;
import fr.iagl.gamification.repository.QcmRepository;

public class QcmServiceImplTest {

	@InjectMocks
	private QcmServiceImpl service;
	
	@Mock
	private QcmRepository repository;
	
	@Mock
	private ClassRepository classRepository;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllQcmCallFindAllFromRepository(){
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<QcmEntity>());
		service.getAllQcm();
		Mockito.verify(repository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testGetAllQcmReturnListOfQcm(){
		QcmModel stm1 = Mockito.mock(QcmModel.class);
		QcmModel stm2 = Mockito.mock(QcmModel.class);
		QcmModel stm3 = Mockito.mock(QcmModel.class);
		QcmEntity ste1 = Mockito.mock(QcmEntity.class);
		QcmEntity ste2 = Mockito.mock(QcmEntity.class);
		QcmEntity ste3 = Mockito.mock(QcmEntity.class);
		Mockito.when(mapper.map(ste1, QcmModel.class)).thenReturn(stm1);
		Mockito.when(mapper.map(ste2, QcmModel.class)).thenReturn(stm2);
		Mockito.when(mapper.map(ste3, QcmModel.class)).thenReturn(stm3);
		Mockito.when(repository.findAll()).thenReturn(Arrays.asList(new QcmEntity[]{ste1,ste2,ste3}));
		List<QcmModel> result = service.getAllQcm();
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(stm1));
		Assert.assertTrue(result.contains(stm2));
		Assert.assertTrue(result.contains(stm3));
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveQcmClassroomDoesntExists() throws GamificationServiceException {
		QcmModel model = Mockito.mock(QcmModel.class);
		service.saveQcm(model, 2L);
	}
	
	@Test
	public void testSaveQcmOK() throws GamificationServiceException {
		QcmModel model = Mockito.mock(QcmModel.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		QcmEntity qcmEntity = Mockito.mock(QcmEntity.class);
		Mockito.when(classRepository.findOne(Mockito.anyLong())).thenReturn(classEntity);
		Mockito.when(mapper.map(model, QcmEntity.class)).thenReturn(qcmEntity);
		service.saveQcm(model, 2L);
		Mockito.verify(repository).save(Mockito.any(QcmEntity.class));
	}
	
	@Test
	public void testSaveQcmMultipleQuestionAndResponseOK() throws GamificationServiceException {
		QcmModel model = Mockito.mock(QcmModel.class);
		ClassEntity classEntity = Mockito.mock(ClassEntity.class);
		QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);
		QcmEntity qcmEntity = Mockito.mock(QcmEntity.class);
		QuestionModel questionModel = Mockito.mock(QuestionModel.class);
		AnswerModel answerModel = Mockito.mock(AnswerModel.class);
		AnswerEntity answerEntity = Mockito.mock(AnswerEntity.class);
		Mockito.when(classRepository.findOne(Mockito.anyLong())).thenReturn(classEntity);
		Mockito.when(model.getQuestions()).thenReturn(Arrays.asList(questionModel));
		Mockito.when(mapper.map(questionModel, QuestionEntity.class)).thenReturn(questionEntity);
		Mockito.when(questionModel.getAnswers()).thenReturn(Arrays.asList(answerModel));
		Mockito.when(mapper.map(model, QcmEntity.class)).thenReturn(qcmEntity);
		Mockito.when(mapper.map(answerModel, AnswerEntity.class)).thenReturn(answerEntity);
		service.saveQcm(model, 2L);
		Mockito.verify(repository).save(Mockito.any(QcmEntity.class));
	}
	
	@Test
	public void testGetAllQcmByClassCallFindByClassFromRepository() throws GamificationServiceException{
		Mockito.when(repository.findByClass(Mockito.anyLong())).thenReturn( new ArrayList<QcmEntity>());
		service.getAllQcmByClass(Mockito.anyLong());
		Mockito.verify(repository, Mockito.times(1)).findByClass(Mockito.anyLong());
	}
	
	@Test
	public void testGetAllQcmByClassThrowException() throws GamificationServiceException{
		Mockito.when(repository.findByClass(Mockito.anyLong())).thenReturn( new ArrayList<QcmEntity>());
		service.getAllQcmByClass(Mockito.anyLong());
	}
	
	@Test
	public void testgetAllQcmByClassReturnListOfQcm() throws GamificationServiceException{
		
		QcmModel qmodel1 = Mockito.mock(QcmModel.class);
		QcmEntity qentity1 = Mockito.mock(QcmEntity.class);
		QcmModel qmodel2 = Mockito.mock(QcmModel.class);
		QcmEntity qentity2 = Mockito.mock(QcmEntity.class);
		QcmModel qmodel3 = Mockito.mock(QcmModel.class);
		QcmEntity qentity3 = Mockito.mock(QcmEntity.class);
		
		Mockito.when(mapper.map(qentity1, QcmModel.class)).thenReturn(qmodel1);
		Mockito.when(mapper.map(qentity2, QcmModel.class)).thenReturn(qmodel2);
		Mockito.when(mapper.map(qentity3, QcmModel.class)).thenReturn(qmodel3);
		Mockito.when(repository.findByClass(Mockito.anyLong())).thenReturn(Arrays.asList(new QcmEntity[]{qentity1,qentity2,qentity3}));
		
		List<QcmModel> resultat = service.getAllQcmByClass(1L);
		
		Assert.assertEquals(3,resultat.size());
		Assert.assertTrue(resultat.contains(qmodel1));
		Assert.assertTrue(resultat.contains(qmodel2));
		Assert.assertTrue(resultat.contains(qmodel3));

	}
}
