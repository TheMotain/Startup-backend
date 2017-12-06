package fr.iagl.gamification.services.impl;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import fr.iagl.gamification.entity.AnswerEntity;
import fr.iagl.gamification.entity.QcmEntity;
import fr.iagl.gamification.entity.QuestionEntity;
import fr.iagl.gamification.repository.AnswerRepository;
import fr.iagl.gamification.utils.ChannelEnum;

public class RunnableResultQcmServiceImplTest {

	@InjectMocks
	private RunnableResultQcmServiceImpl runnableResultQcmServiceImpl;
	
	@Mock
	public SimpMessageSendingOperations messagingTemplate;
	  
	@Mock
	private AnswerRepository answerRepository;
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
    public void testRunMethod() {
	    JSONObject obj = Mockito.mock(JSONObject.class);
	    AnswerEntity answer = Mockito.mock(AnswerEntity.class);
	    QuestionEntity question = Mockito.mock(QuestionEntity.class);
	    QcmEntity qcm = Mockito.mock(QcmEntity.class);
	    Mockito.when(obj.getLong("pupil")).thenReturn(1L);
	    Mockito.when(obj.getLong("answer")).thenReturn(3L);
	    Mockito.when(answerRepository.findOne(3L)).thenReturn(answer);
	    Mockito.when(answer.getQuestion()).thenReturn(question);
	    Mockito.when(question.getQcm()).thenReturn(qcm);
	    Mockito.when(qcm.getId()).thenReturn(2L);
	    
	    runnableResultQcmServiceImpl.runMethod(obj);
	    Mockito.verify(messagingTemplate,Mockito.times(1)).convertAndSend(Mockito.matches(ChannelEnum.NOTIFICATION_RESULT_QCM.getFullChannelURLWithID("2")),(Map<String, Long>)Mockito.anyObject());
    }
	
	@Test
    public void testRunMethodKOQcmNotFound() {
	    JSONObject obj = Mockito.mock(JSONObject.class);
	    AnswerEntity answer = Mockito.mock(AnswerEntity.class);
	    QuestionEntity question = Mockito.mock(QuestionEntity.class);
	    QcmEntity qcm = Mockito.mock(QcmEntity.class);
	    Mockito.when(obj.getLong("pupil")).thenReturn(1L);
	    Mockito.when(obj.getLong("answer")).thenReturn(3L);
	    Mockito.when(answerRepository.findOne(3L)).thenReturn(answer);
	    Mockito.when(answer.getQuestion()).thenReturn(question);
	    
	    runnableResultQcmServiceImpl.runMethod(obj);
	    Mockito.verify(messagingTemplate,Mockito.never()).convertAndSend(Mockito.matches(ChannelEnum.NOTIFICATION_RESULT_QCM.getFullChannelURLWithID("2")),(Map<String, Long>)Mockito.anyObject());
    }
	
	@Test
    public void testRunMethodKOQuestionNotFound() {
	    JSONObject obj = Mockito.mock(JSONObject.class);
	    AnswerEntity answer = Mockito.mock(AnswerEntity.class);
	    Mockito.when(obj.getLong("pupil")).thenReturn(1L);
	    Mockito.when(obj.getLong("answer")).thenReturn(3L);
	    Mockito.when(answerRepository.findOne(3L)).thenReturn(answer);
	    
	    runnableResultQcmServiceImpl.runMethod(obj);
	    Mockito.verify(messagingTemplate,Mockito.never()).convertAndSend(Mockito.matches(ChannelEnum.NOTIFICATION_RESULT_QCM.getFullChannelURLWithID("2")),(Map<String, Long>)Mockito.anyObject());
    }
	
	@Test
    public void testRunMethodKOAnswerNotFound() {
	    JSONObject obj = Mockito.mock(JSONObject.class);
	    Mockito.when(obj.getLong("pupil")).thenReturn(1L);
	    Mockito.when(obj.getLong("answer")).thenReturn(3L);
	    
	    runnableResultQcmServiceImpl.runMethod(obj);
	    Mockito.verify(messagingTemplate,Mockito.never()).convertAndSend(Mockito.matches(ChannelEnum.NOTIFICATION_RESULT_QCM.getFullChannelURLWithID("2")),(Map<String, Long>)Mockito.anyObject());
    }
	  
}
