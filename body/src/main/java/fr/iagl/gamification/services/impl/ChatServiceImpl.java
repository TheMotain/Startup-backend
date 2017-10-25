package fr.iagl.gamification.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.MessageEntity;
import fr.iagl.gamification.model.MessageModel;
import fr.iagl.gamification.repository.MessageRepository;
import fr.iagl.gamification.services.ChatService;

/**
 * Implémentation du service qui traite les message reçu par le chat
 * 
 * @author Hélène Meyer
 *
 */
@Service
public class ChatServiceImpl implements ChatService {

	/**
	 * Repository de la table message
	 */
	@Autowired
	private MessageRepository repository;
	
	@Override
	public void saveMessage(final MessageModel chatMessage) {
		repository.save(new MessageEntity(chatMessage.getSender(), chatMessage.getContent()));
	}

}
