package fr.iagl.gamification.services.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import fr.iagl.gamification.data.entity.MessageEntity;
import fr.iagl.gamification.data.repository.MessageRepository;
import fr.iagl.gamification.model.MessageModel;

public class ChatServiceImplTest {

	@InjectMocks
    private ChatServiceImpl chat;

	@Mock
	public MessageRepository repository;

	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
    }


	@Test
	public void test() {
		MessageModel mess = mock(MessageModel.class);
		doReturn("sender").when(mess).getSender();
		doReturn("content").when(mess).getContent();
		doReturn(null).when(repository).save(any(MessageEntity.class));
		chat.saveMessage(mess);

		verify(repository).save(any(MessageEntity.class));
	}

}
