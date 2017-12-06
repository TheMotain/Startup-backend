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
	 * @param lst
	 * @param idStudent
	 * @return
	 * @throws GamificationServiceException
	 */
	public List<ResultQcmModel> saveResultQcm(List<Long> lst, Long idStudent) throws GamificationServiceException;

}
