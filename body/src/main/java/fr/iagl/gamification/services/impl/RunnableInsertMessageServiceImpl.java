package fr.iagl.gamification.services.impl;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.Singleton;
import fr.iagl.gamification.model.MessageModel;
import fr.iagl.gamification.model.MessageModel.MessageType;
import fr.iagl.gamification.services.RunnableHashMapService;

/**
 * Traitement de la notification de l'insertion d'un message en base de donnée
 * 
 * @author Hélène Meyer
 *
 */
@Service("runnableInsertMessageServiceImpl")
public class RunnableInsertMessageServiceImpl implements RunnableHashMapService{
	
	public static final Logger LOGGER = Logger.getLogger(RunnableInsertMessageServiceImpl.class);

	/**
	 * broadcast un message dans un cannal 
	 */
	@Autowired
    public SimpMessageSendingOperations messagingTemplate;

	@Override
	public void runMethod(JSONObject json) throws JSONException {
		LOGGER.info("Envoi du message à " + Singleton.sessions.keySet().iterator().next());
		MessageModel message = new MessageModel(MessageType.CHAT, json.getString("content"), json.getString("sender"));
		messagingTemplate.convertAndSendToUser(Singleton.sessions.keySet().iterator().next(),"/user/channel/notification", message);
	}
}
