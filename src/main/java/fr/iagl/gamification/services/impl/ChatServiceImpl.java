package fr.iagl.gamification.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.iagl.gamification.data.entity.MessageEntity;
import fr.iagl.gamification.data.repository.MessageRepository;
import fr.iagl.gamification.model.MessageModel;
import fr.iagl.gamification.services.ChatService;

/**
 * Implémentation du service qui traite les message reçu par le chat
 * 
 * @author Hélène Meyer
 *
 */
@Component
public class ChatServiceImpl implements ChatService {

	/**
	 * Repository de la table message
	 */
	@Autowired
	MessageRepository repository;
	
	@Override
	public void saveMessage(MessageModel chatMessage) {
		repository.save(new MessageEntity(chatMessage.getSender(), chatMessage.getContent()));
	}

}
