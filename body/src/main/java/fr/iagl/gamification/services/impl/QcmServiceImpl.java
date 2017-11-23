package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.repository.QcmRepository;
import fr.iagl.gamification.services.QcmService;

/**
 * Service du QCM
 *
 * @author Hélène MEYER
 *
 */
public class QcmServiceImpl implements QcmService {

	/**
	 * Repository du QCM
	 */
	@Autowired
	private QcmRepository repository;
	
	/**
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public List<QcmModel> getAllQcm() {
		List<QcmModel> output = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(x -> output.add(mapper.map(x, QcmModel.class)));
		return output;
	}

	@Override
	public QcmModel saveQcm(QcmModel model, long idClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
