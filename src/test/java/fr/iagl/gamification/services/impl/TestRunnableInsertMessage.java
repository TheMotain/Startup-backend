package fr.iagl.gamification.services.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import com.google.gson.JsonObject;

import fr.iagl.gamification.model.MessageModel;

public class TestRunnableInsertMessage {
	
	@Mock
	public SimpMessageSendingOperations messagingTemplate;
	
	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testRunMethod() throws JSONException {
		JSONObject json = mock(JSONObject.class);
		doReturn("blabla").when(json).getString(anyString());
		RunnableInsertMessageServiceImpl run = new RunnableInsertMessageServiceImpl();
		
		run.runMethod(json, messagingTemplate);
		verify(messagingTemplate).convertAndSend(anyString(), any(MessageModel.class));
	}
	
}
