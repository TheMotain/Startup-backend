package fr.iagl.gamification.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.model.MessageModel;
import fr.iagl.gamification.services.ChatService;

public class TestChatController {

	@InjectMocks
    private ChatController chatController;
	
	@Mock
	private ChatService chatService;
 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSendMessage() {
		MessageModel mess = mock(MessageModel.class);
		chatController.sendMessage(mess);
		verify(chatService).saveMessage(any(MessageModel.class));
	}

}
