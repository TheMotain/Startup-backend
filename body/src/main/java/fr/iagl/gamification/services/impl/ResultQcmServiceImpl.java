package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.AnswerEntity;
import fr.iagl.gamification.entity.ResultQcmEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.ResultQcmModel;
import fr.iagl.gamification.repository.AnswerRepository;
import fr.iagl.gamification.repository.ResultQcmRepository;
import fr.iagl.gamification.repository.StudentRepository;
import fr.iagl.gamification.services.ResultQcmService;

/**
 * Service gérant les résultats du QCM
 *
 * @author Hélène MEYER
 *
 */
@Service
public class ResultQcmServiceImpl implements ResultQcmService{

	/**
	 * repository de la table result_qcm
	 */
	@Autowired
	private ResultQcmRepository repository;
	
	/**
	 * repository de la table answer
	 */
	@Autowired
	private AnswerRepository answerRepository;
	
	/**
	 * repository de la table student
	 */
	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * Mapper Model <-> Entité
	 */
	@Autowired
	private Mapper mapper;
	
	@Override
	public List<ResultQcmModel> getAllQcmResultsByIdQcm(Long id) {
		List<ResultQcmModel> output = new ArrayList<>();
		repository.findByAnswer_Question_Qcm_Id(id).iterator().forEachRemaining(x -> output.add(mapper.map(x, ResultQcmModel.class)));
		return output;
	}

	@Override
	public List<ResultQcmModel> saveResultQcm(List<Long> lst, Long idStudent) throws GamificationServiceException {

		if (CollectionUtils.isEmpty(lst) || idStudent == null) {
			throw new GamificationServiceException(Arrays.asList("Paramètres invalides"));
		}
		StudentEntity student = studentRepository.findOne(idStudent);
		if (student == null) {
			throw new GamificationServiceException(Arrays.asList("Eleve non trouvé ["+ idStudent + "]"));
		}
			
		List<ResultQcmEntity> lstToAdd = new ArrayList<>();
		for (Long idAnswer : lst) {
			if (repository.findByAnswer_IdAndStudent_Id(idAnswer, idStudent) != null) {
				throw new GamificationServiceException(Arrays.asList("L'élève [" + idStudent + "] a déjà répondu à cette question [" + idAnswer + "]"));
			}
			AnswerEntity answer = answerRepository.findOne(idAnswer);
			if (answer == null) {
				throw new GamificationServiceException(Arrays.asList("La réponse ["+ idAnswer +"] n'existe pas en base de données"));
			}
			ResultQcmEntity entity = new ResultQcmEntity();
			entity.setAnswer(answer);
			entity.setStudent(student);
			lstToAdd.add(entity);
		}
		
		Iterable<ResultQcmEntity> result = repository.save(lstToAdd);
		List<ResultQcmModel> resultModel = new ArrayList<>();
		result.iterator().forEachRemaining(x -> resultModel.add(mapper.map(x, ResultQcmModel.class)));
		return resultModel;
	}

}
