package fr.iagl.gamification.services.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.QcmEntity;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.repository.QcmRepository;
import fr.iagl.gamification.services.RunnableHashMapService;
import fr.iagl.gamification.utils.ChannelEnum;

/**
 * Envoie le QCM sur la web socket
 *
 * @author Hélène MEYER
 *
 */
@Service("runnableQcmServiceImpl")
public class RunnableQcmServiceImpl implements RunnableHashMapService {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(RunnableQcmServiceImpl.class);

	/**
	 * broadcast un message dans un cannal
	 */
	@Autowired
	public SimpMessageSendingOperations messagingTemplate;

	/**
	* repository du qcm
	*/
	@Autowired
	private QcmRepository qcmRepository;

	/**
	 * mapper entity <-> model
	 */
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	@Transactional
	public void runMethod(JSONObject json) throws JSONException {
		LOGGER.info("Runnable Get QCM WS : " + json);
		Long qcmID = json.getLong("id");
		Long classroomID = json.getLong("classroom");
		QcmEntity qcm = qcmRepository.findOne(qcmID);
		QcmModel qcmModel = mapper.map(qcm, QcmModel.class);
		messagingTemplate.convertAndSend(
				ChannelEnum.NOTIFICATION_QCM.getFullChannelURLWithID(String.valueOf(classroomID)), qcmModel);
	}
}