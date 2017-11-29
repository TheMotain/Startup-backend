package fr.iagl.gamification.services.impl;
 
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
 
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.utils.ChannelEnum;
 
public class RunnablePointServiceImplTest {
  
  @InjectMocks
  private RunnablePointServiceImpl runnablePointServiceImpl;
  
  @Mock
  private SimpMessageSendingOperations messagingTemplate;
  
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testRunMethod() {
    JSONObject obj = Mockito.mock(JSONObject.class);
    Mockito.when(obj.getLong("pupil")).thenReturn(1L);
    runnablePointServiceImpl.runMethod(obj);
    Mockito.verify(obj,Mockito.times(1)).getLong("pupil");
    Mockito.verify(obj,Mockito.times(1)).getLong("malus");
    Mockito.verify(obj,Mockito.times(1)).getLong("bonus");
    Mockito.verify(messagingTemplate,Mockito.times(1)).convertAndSend(Mockito.matches(ChannelEnum.NOTIFICATION_POINT.getFullChannelURLWithID("1")),(PointModel)Mockito.anyObject());
  }
}