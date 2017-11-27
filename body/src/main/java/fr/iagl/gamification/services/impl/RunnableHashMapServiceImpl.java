package fr.iagl.gamification.services.impl;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.services.RunnableHashMapService;
import fr.iagl.gamification.utils.ChannelEnum;

@Service
public class RunnableHashMapServiceImpl implements RunnableHashMapService{
	
	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(RunnablePointServiceImpl.class);

	/**
	 * broadcast un message dans un cannal 
	 */
	@Autowired
    public SimpMessageSendingOperations messagingTemplate;

	@Override
	public void runMethod(JSONObject json) throws JSONException {
		PointModel message = new PointModel(json);
		messagingTemplate.convertAndSend(ChannelEnum.NOTIFICATION_POINT.getFullChannelURLUserID(message.getStudent().getId().toString()), message);
	}

}
