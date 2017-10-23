package fr.iagl.gamification.services.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import fr.iagl.gamification.model.MessageModel;
import fr.iagl.gamification.model.MessageModel.MessageType;
import fr.iagl.gamification.services.RunnableHashMap;

/**
 * Traitement de la notification de l'insertion d'un message en base de donnée
 * 
 * @author Hélène Meyer
 *
 */
@Component
public class RunnableInsertMessageServiceImpl implements RunnableHashMap{
	
	/**
	 * broadcast un message dans un cannal 
	 */
	@Autowired
    public SimpMessageSendingOperations messagingTemplate;

	@Override
	public void runMethod(JSONObject json, SimpMessageSendingOperations simpMessageSendingOperations) throws JSONException {
		MessageModel message = new MessageModel(MessageType.CHAT, json.getString("content"), json.getString("sender"));
		simpMessageSendingOperations.convertAndSend("/channel/public", message);
	}

}
