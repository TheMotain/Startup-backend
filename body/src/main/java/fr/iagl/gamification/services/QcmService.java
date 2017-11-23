package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.model.QcmModel;

/**
 * Service pour les actions du QCM
 *
 * @author Hélène MEYER
 *
 */
public interface QcmService {

	/**
	 * Récupère tous les QCM
	 * 
	 * @return tous les QCM
	 */
	public List<QcmModel> getAllQcm();
	
	/**
	 * Enregistre le QCM
	 * 
	 * @param model le qcm à sauvegarder
	 * @param idClass l'identifiant de la classe
	 * @return le qcm enregistré
	 */
	public QcmModel saveQcm(QcmModel model, long idClass);
}
