package fr.iagl.gamification.services.impl;

import static org.junit.Assert.assertNotNull;

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
import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.entity.QuestionEntity;
import fr.iagl.gamification.entity.ResultQcmEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.ResultQcmModel;
import fr.iagl.gamification.repository.AnswerRepository;
import fr.iagl.gamification.repository.PointRepository;
import fr.iagl.gamification.repository.ResultQcmRepository;
import fr.iagl.gamification.repository.StudentRepository;

public class ResultQcmServiceImplTest {

	@InjectMocks
	private ResultQcmServiceImpl service;
	
	@Mock
	private ResultQcmRepository repository;
	
	@Mock
	private AnswerRepository answerRepository;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private PointRepository pointRepository;
	
	@Mock
	private Mapper mapper;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllQcmCallFindAnswerByQcmIdFromRepository(){
		Mockito.when(repository.findByAnswer_Question_Qcm_Id(2L)).thenReturn(new ArrayList<ResultQcmEntity>());
		service.getAllQcmResultsByIdQcm(2L);
		Mockito.verify(repository, Mockito.times(1)).findByAnswer_Question_Qcm_Id(2L);
	}
	
	@Test
	public void testGetAllResultQcmReturnListOfResultQcm(){
		ResultQcmModel stm1 = Mockito.mock(ResultQcmModel.class);
		ResultQcmModel stm2 = Mockito.mock(ResultQcmModel.class);
		ResultQcmModel stm3 = Mockito.mock(ResultQcmModel.class);
		ResultQcmEntity ste1 = Mockito.mock(ResultQcmEntity.class);
		ResultQcmEntity ste2 = Mockito.mock(ResultQcmEntity.class);
		ResultQcmEntity ste3 = Mockito.mock(ResultQcmEntity.class);
		Mockito.when(mapper.map(ste1, ResultQcmModel.class)).thenReturn(stm1);
		Mockito.when(mapper.map(ste2, ResultQcmModel.class)).thenReturn(stm2);
		Mockito.when(mapper.map(ste3, ResultQcmModel.class)).thenReturn(stm3);
		Mockito.when(repository.findByAnswer_Question_Qcm_Id(2L)).thenReturn(Arrays.asList(new ResultQcmEntity[]{ste1,ste2,ste3}));
		List<ResultQcmModel> result = service.getAllQcmResultsByIdQcm(2L);
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains(stm1));
		Assert.assertTrue(result.contains(stm2));
		Assert.assertTrue(result.contains(stm3));
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmStudentAndListNotValid() throws GamificationServiceException {
		service.saveResultQcm(new ArrayList<>(), null);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmStudentNotValid() throws GamificationServiceException {
		service.saveResultQcm(Arrays.asList(2L), null);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmListNotValid() throws GamificationServiceException {
		service.saveResultQcm(new ArrayList<>(), 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmStudentNotFound() throws GamificationServiceException {
		service.saveResultQcm(Arrays.asList(Mockito.anyLong()), 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmResultAlreadyExists() throws GamificationServiceException {
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		Mockito.doReturn(Arrays.asList(Mockito.mock(ResultQcmEntity.class))).when(repository).findByStudent_Id(2L);
		service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmResultWhenAnswerNotExist() throws GamificationServiceException {
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmResultWhenAnswerNotExistAndBadRecupAnswer() throws GamificationServiceException {
		ResultQcmEntity result = Mockito.mock(ResultQcmEntity.class);
		AnswerEntity answer = Mockito.mock(AnswerEntity.class);
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		Mockito.doReturn(Arrays.asList(result)).when(repository).findByStudent_Id(2L);
		Mockito.doReturn(answer).when(result).getAnswer();
		service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmResultWhenAnswerNotExistAndBadRecupAnswerBadQuestion() throws GamificationServiceException {
		ResultQcmEntity result = Mockito.mock(ResultQcmEntity.class);
		AnswerEntity answer = Mockito.mock(AnswerEntity.class);
		QuestionEntity question = Mockito.mock(QuestionEntity.class);
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		Mockito.doReturn(Arrays.asList(result)).when(repository).findByStudent_Id(2L);
		Mockito.doReturn(answer).when(result).getAnswer();
		Mockito.doReturn(question).when(answer).getQuestion();
		service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
	}
	
	@Test(expected=GamificationServiceException.class)
	public void testSaveResultQcmResultDejaReponduALaQuestion() throws GamificationServiceException {
		ResultQcmEntity resultats = Mockito.mock(ResultQcmEntity.class);
		AnswerEntity answer = Mockito.mock(AnswerEntity.class);
		AnswerEntity answerRep = Mockito.mock(AnswerEntity.class);
		QuestionEntity questionRep = Mockito.mock(QuestionEntity.class);
		QuestionEntity question = Mockito.mock(QuestionEntity.class);
		Iterable<ResultQcmEntity> iter = Arrays.asList(resultats);
		Iterable<ResultQcmEntity> answers = Arrays.asList(resultats);
		Mockito.doReturn(answer).when(resultats).getAnswer();
		Mockito.doReturn(question).when(answer).getQuestion();
		Mockito.doReturn(5L).when(question).getId();
		Mockito.doReturn(Arrays.asList(resultats)).when(repository).findByStudent_Id(Mockito.anyLong());
		
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		Mockito.doReturn(answerRep).when(answerRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(questionRep).when(answerRep).getQuestion();
		Mockito.doReturn(5L).when(questionRep).getId();
		
		List<ResultQcmModel> results = service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
	}
	
	@Test
	public void testSaveResultQcmResultOKScore0() throws GamificationServiceException {
		ResultQcmEntity resultats = Mockito.mock(ResultQcmEntity.class);
		AnswerEntity answer = Mockito.mock(AnswerEntity.class);
		AnswerEntity answerRep = Mockito.mock(AnswerEntity.class);
		QuestionEntity questionRep = Mockito.mock(QuestionEntity.class);
		QuestionEntity question = Mockito.mock(QuestionEntity.class);
		Iterable<ResultQcmEntity> iter = Arrays.asList(resultats);
		Iterable<ResultQcmEntity> answers = Arrays.asList(resultats);
		Mockito.doReturn(answer).when(resultats).getAnswer();
		Mockito.doReturn(question).when(answer).getQuestion();
		Mockito.doReturn(5L).when(question).getId();
		Mockito.doReturn(Arrays.asList(resultats)).when(repository).findByStudent_Id(Mockito.anyLong());
		
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		Mockito.doReturn(answerRep).when(answerRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(questionRep).when(answerRep).getQuestion();
		Mockito.doReturn(2L).when(questionRep).getId();
		
		Mockito.doReturn(iter).when(repository).save(Mockito.anyCollection());
		Mockito.doReturn(Mockito.mock(ResultQcmModel.class)).when(mapper).map(resultats, ResultQcmModel.class);
		
		List<ResultQcmModel> results = service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
		assertNotNull(results);
		Mockito.verify(repository).save(Mockito.anyCollection());
	}
	
	
	@Test
	public void testSaveResultQcmResultOKScore2() throws GamificationServiceException {
		ResultQcmEntity resultats = Mockito.mock(ResultQcmEntity.class);
		AnswerEntity answer = Mockito.mock(AnswerEntity.class);
		QuestionEntity question = Mockito.mock(QuestionEntity.class);
		Iterable<ResultQcmEntity> iter = Arrays.asList(resultats);
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		Mockito.doReturn(answer).when(answerRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(iter).when(repository).save(Mockito.anyCollection());
		Mockito.doReturn(Mockito.mock(ResultQcmModel.class)).when(mapper).map(resultats, ResultQcmModel.class);
		Mockito.doReturn(true).when(answer).isGood();
		Mockito.doReturn(question).when(answer).getQuestion();
		Mockito.doReturn(2).when(question).getNbPoints();
		Mockito.doReturn(Mockito.mock(PointEntity.class)).when(pointRepository).findByStudent_Id(2L);
		List<ResultQcmModel> results = service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
		assertNotNull(results);
		Mockito.verify(repository).save(Mockito.anyCollection());
		Mockito.verify(pointRepository).save(Mockito.any(PointEntity.class));
	}
	
	@Test
	public void testSaveResultQcmResultOKScore2PointNotExists() throws GamificationServiceException {
		ResultQcmEntity resultats = Mockito.mock(ResultQcmEntity.class);
		AnswerEntity answer = Mockito.mock(AnswerEntity.class);
		QuestionEntity question = Mockito.mock(QuestionEntity.class);
		Iterable<ResultQcmEntity> iter = Arrays.asList(resultats);
		Mockito.doReturn(Mockito.mock(StudentEntity.class)).when(studentRepository).findOne(2L);
		Mockito.doReturn(answer).when(answerRepository).findOne(Mockito.anyLong());
		Mockito.doReturn(iter).when(repository).save(Mockito.anyCollection());
		Mockito.doReturn(Mockito.mock(ResultQcmModel.class)).when(mapper).map(resultats, ResultQcmModel.class);
		Mockito.doReturn(true).when(answer).isGood();
		Mockito.doReturn(question).when(answer).getQuestion();
		Mockito.doReturn(2).when(question).getNbPoints();
		List<ResultQcmModel> results = service.saveResultQcm(Arrays.asList(1L, 3L), 2L);
		assertNotNull(results);
		Mockito.verify(repository).save(Mockito.anyCollection());
		Mockito.verify(pointRepository).save(Mockito.any(PointEntity.class));
	}
}
