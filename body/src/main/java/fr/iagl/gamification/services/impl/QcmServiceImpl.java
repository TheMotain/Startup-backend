package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.entity.QcmEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.repository.ClassRepository;
import fr.iagl.gamification.repository.QcmRepository;
import fr.iagl.gamification.services.QcmService;

/**
 * Service du QCM
 *
 * @author Hélène MEYER
 *
 */
@Service
public class QcmServiceImpl implements QcmService {

	/**
	 * Repository du QCM
	 */
	@Autowired
	private QcmRepository repository;
	
	private ClassRepository classRepository;
	
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
	public QcmModel saveQcm(QcmModel model) throws GamificationServiceException {
		if (model.getClassroom() == null || model.getClassroom().getId() == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM));
		}
		ClassEntity classroom = classRepository.findOne(model.getClassroom().getId());
		if (classroom == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM));
		}
		QcmEntity entity = mapper.map(model, QcmEntity.class);
		repository.save(entity);
		return mapper.map(entity, QcmModel.class);
	}

}
