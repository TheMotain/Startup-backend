package fr.iagl.gamification.services;

import fr.iagl.gamification.model.MessageModel;

/**
 * Service du chat
 * 
 * @author Hélène Meyer
 *
 */
public interface ChatService {

	/**
	 * Enregistre le message reçu
	 * 
	 * @param message le message reçu
	 */
	public void saveMessage(MessageModel message);
}
