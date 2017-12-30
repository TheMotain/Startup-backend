package fr.iagl.gamification.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.AnswerEntity;
import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.entity.ResultQcmEntity;
import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.ResultQcmModel;
import fr.iagl.gamification.repository.AnswerRepository;
import fr.iagl.gamification.repository.PointRepository;
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

	private static final BigDecimal ARGENT_A_GAGNER_BONNE_REPONSE = BigDecimal.valueOf(0.5);
	private static final int POINTS_PAR_QUESTION = 5;

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
	
	@Autowired
	private PointRepository pointRepository;
	
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
		Long score = 0L;
		BigDecimal argent = new BigDecimal(0);
		List<ResultQcmEntity> lstToAdd = new ArrayList<>();
		
		List<Long> questions = questionsRepondues(idStudent);
		BigDecimal argentAgagner = new BigDecimal(0);
		
		for (Long idAnswer : lst) {
			AnswerEntity answer = answerRepository.findOne(idAnswer);
			
			gestionErreur(idStudent, idAnswer, questions, answer);
			ResultQcmEntity entity = new ResultQcmEntity();
			entity.setAnswer(answer);
			entity.setStudent(student);
			
			if (answer.isGood()) {
				argentAgagner = argent.add(ARGENT_A_GAGNER_BONNE_REPONSE);
				score += POINTS_PAR_QUESTION;
			}
			lstToAdd.add(entity);
		}
		
		Iterable<ResultQcmEntity> result = repository.save(lstToAdd);
		saveScore(idStudent, student, score, argentAgagner);
		
		List<ResultQcmModel> resultModel = new ArrayList<>();
		result.iterator().forEachRemaining(x -> resultModel.add(mapper.map(x, ResultQcmModel.class)));
		return resultModel;
	}

	/**
	 * Récupération de l'ensemble des questions déjà répondues par l'élève
	 * 
	 * @param idStudent identifiant de l'élève
	 * @return la liste des identifiants des questions
	 */
	private List<Long> questionsRepondues(Long idStudent) {
		List<ResultQcmEntity> answers = repository.findByStudent_Id(idStudent);
		List<Long> questions = new ArrayList<>();
		answers.iterator().forEachRemaining(a -> {
			if (a.getAnswer() != null && a.getAnswer().getQuestion() != null) {
				questions.add(a.getAnswer().getQuestion().getId());
			}
		});
		return questions;
	}

	/**
	 * Génère une exception si le choix n'existe pas en base de donnée ou si l'élève a déjà répondu à la question
	 * 
	 * @param idStudent identifiant de l'élève
	 * @param idAnswer identifiant du choix
	 * @param questions liste des identifiants des questions
	 * @param answer choix
	 * @throws GamificationServiceException si le choix n'existe pas en base de donnée ou si l'élève a déjà répondu à la question
	 */
	private void gestionErreur(Long idStudent, Long idAnswer, List<Long> questions, AnswerEntity answer)
			throws GamificationServiceException {
		if (answer == null) {
			throw new GamificationServiceException(Arrays.asList("La réponse ["+ idAnswer +"] n'existe pas en base de données"));
		}
		if (questions.contains(answer.getQuestion().getId())) {
			throw new GamificationServiceException(Arrays.asList("L'élève [" + idStudent + "] a déjà répondu à cette question [" + answer.getQuestion().getId() + "]"));
		}
	}

	/**
	 * Enregistre le score de l'étudiant ayant bien répondu
	 * 
	 * @param idStudent l'identifiant de l'étudiant
	 * @param student élève
	 * @param score score du qcm
	 * @param argent argent de l'élève
	 */
	private void saveScore(Long idStudent, StudentEntity student, Long score, BigDecimal argent) {
		if (score != 0) {
			PointEntity points = pointRepository.findByStudent_Id(idStudent);
			if (points == null) {
				points = new PointEntity();
				points.setStudent(student);
			}

			if (points.getArgent() != null) {
				points.setArgent(points.getArgent().add(argent));
			} else {
				points.setArgent(argent);
			}
			points.setBonus(points.getBonus() + score);
			
			pointRepository.save(points);
		}
	}

}
