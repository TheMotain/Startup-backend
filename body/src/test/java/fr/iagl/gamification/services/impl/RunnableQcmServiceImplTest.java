package fr.iagl.gamification.services.impl;

import org.dozer.DozerBeanMapper;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import fr.iagl.gamification.entity.QcmEntity;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.repository.QcmRepository;
import fr.iagl.gamification.utils.ChannelEnum;

public class RunnableQcmServiceImplTest {

	@InjectMocks
	private RunnableQcmServiceImpl runnableQcmServiceImpl;
	
	@Mock
	public SimpMessageSendingOperations messagingTemplate;
	  
	@Mock
	private QcmRepository qcmRepository;
	  
	@Mock
	private DozerBeanMapper mapper;
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
    public void testRunMethod() {
	    JSONObject obj = Mockito.mock(JSONObject.class);
	    QcmEntity qcm = Mockito.mock(QcmEntity.class);
	    Mockito.when(obj.getLong("id")).thenReturn(1L);
	    Mockito.when(obj.getLong("classroom")).thenReturn(2L);
	    Mockito.when(qcmRepository.findOne(1L)).thenReturn(qcm);
	    runnableQcmServiceImpl.runMethod(obj);
	    Mockito.verify(messagingTemplate,Mockito.times(1)).convertAndSend(Mockito.matches(ChannelEnum.NOTIFICATION_QCM.getFullChannelURLWithID("2")),(QcmModel)Mockito.anyObject());
    }
	  
}
