package fr.iagl.gamification.services.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.entity.AnswerEntity;
import fr.iagl.gamification.entity.ClassEntity;
import fr.iagl.gamification.entity.QcmEntity;
import fr.iagl.gamification.entity.QuestionEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AnswerModel;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.model.QuestionModel;
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
	
	@Autowired
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
	public QcmModel saveQcm(QcmModel model, long idClass) throws GamificationServiceException {
		ClassEntity classroom = classRepository.findOne(idClass);

		if (classroom == null) {
			throw new GamificationServiceException(Arrays.asList(CodeError.ERROR_NOT_EXISTS_CLASSROOM));
		}
		QcmEntity entity = mapper.map(model, QcmEntity.class);
		entity.setClassroom(classroom);
		List<QuestionEntity> questions = new ArrayList<>();
		for (QuestionModel q: model.getQuestions()){
			QuestionEntity question = mapper.map(q, QuestionEntity.class);
			List<AnswerEntity> answers = new ArrayList<>();
			for (AnswerModel a: q.getAnswers()) {
				AnswerEntity answer = mapper.map(a, AnswerEntity.class);
				answer.setQuestion(question);
				answers.add(answer);
			}
			question.setAnswers(answers);
			question.setQcm(entity);
			questions.add(question);
		}
		entity.setQuestions(questions);
		
		repository.save(entity);
		return mapper.map(entity, QcmModel.class);
	}

	@Override
	public List<QcmModel> getAllQcmByClass(long idClass) throws GamificationServiceException {	
		List<QcmModel> qcmModel = new ArrayList<>();
		repository.findByClass(idClass).iterator().forEachRemaining(x -> qcmModel.add(mapper.map(x, QcmModel.class)));
		return qcmModel;
	}

}
