package fr.iagl.gamification.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.iagl.gamification.entity.ResultQcmEntity;

/**
 * Repository de la table des resultats du qcm
 *
 * @author Hélène MEYER
 *
 */
public interface ResultQcmRepository extends CrudRepository<ResultQcmEntity, Long>{

	/**
	 * Récupère toutes les réponses des élèves en fonction de l'identifiant d'une classe
	 * 
	 * @param id de la classe
	 * @return la liste de tous les résultats
	 */
	public List<ResultQcmEntity> findByAnswer_Question_Qcm_Id(Long id);

	/**
	 * Récupère la réponse d'un élève grâce à son id et à l'id du choix
	 * 
	 * @param answerId identifiant du choix
	 * @param studentId identifiant de l'élève
	 * @return le resultat du qcm
	 */
	public ResultQcmEntity findByAnswer_IdAndStudent_Id(Long answerId, Long studentId);

	public List<ResultQcmEntity> findByStudent_Id(Long idStudent);
}
