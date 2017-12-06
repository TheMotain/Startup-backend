package fr.iagl.gamification.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.AnswerEntity;
import fr.iagl.gamification.repository.AnswerRepository;
import fr.iagl.gamification.services.RunnableHashMapService;
import fr.iagl.gamification.utils.ChannelEnum;

/***
 * Traite la notification du resultat d'un élève
 * 
 * @author Hélène MEYER
 */
@Service("runnableResultQcmServiceImpl")
public class RunnableResultQcmServiceImpl implements RunnableHashMapService{

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(RunnableResultQcmServiceImpl.class);

	/**
	 * broadcast un message dans un cannal
	 */
	@Autowired
	public SimpMessageSendingOperations messagingTemplate;
	
	/**
	 * Repository de l'objet answer
	 */
	@Autowired
	public AnswerRepository answerRepository;

	@Override
	@Transactional
	public void runMethod(JSONObject json) throws JSONException {
		LOGGER.info("Runnable Get Result QCM WS : " + json);
		Map<String, Long> map = new HashMap<>();
		map.put("idStudent", json.getLong("pupil"));
		map.put("idAnswer", json.getLong("answer"));
		AnswerEntity answer = answerRepository.findOne(json.getLong("answer"));
		
		if (answer != null && answer.getQuestion() != null && answer.getQuestion().getQcm() != null) {
			Long idQcm = answer.getQuestion().getQcm().getId();
			messagingTemplate.convertAndSend(
					ChannelEnum.NOTIFICATION_RESULT_QCM.getFullChannelURLWithID(String.valueOf(idQcm)), map);
		} else {
			LOGGER.warn("Impossible d'envoyer : Result QCM WS : " + json);
		}
		
	}
}
