package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.ResultQcmModel;

/**
 * Service traitant les résultats du QCM
 *
 * @author Hélène MEYER
 *
 */
public interface ResultQcmService {

	/**
	 * Récupérer la liste de tous les résultats d'un QCM
	 * 
	 * @param id du qcm
	 * @return la liste de tous les résultats
	 */
	public List<ResultQcmModel> getAllQcmResultsByIdQcm(Long id);
	
	/**
	 * Enregistre les résultats de l'enfant
	 * 
	 * @param lst la liste des identifiants des élèves
	 * @param idStudent l'identifiant de l'élève
	 * @return la liste des résultats enregistrés
	 * @throws GamificationServiceException  si le choix n'existe pas en base de donnée ou si l'élève a déjà répondu à la question
	 */
	public List<ResultQcmModel> saveResultQcm(List<Long> lst, Long idStudent) throws GamificationServiceException;

}
