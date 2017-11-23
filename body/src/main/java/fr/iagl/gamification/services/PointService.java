package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.exceptions.StudentNotFoundException;
import fr.iagl.gamification.model.PointModel;

/**
 * Service de gestion de point d'un élève
 * 
 * @author Hélène MEYER
 *
 */
public interface PointService {

	/**
	 * Mettre à jour un bonus ou un malus à un élève
	 * 
	 * @param pointToUpdate les points à modifier de l'élève
	 * @param idStudent l'identifiant de l'élève 
	 * @return l'ensemble des points de l'élève
	 * @throws StudentNotFoundException quand l'élève n'existe pas
	 */
	public PointModel updatePoint(PointModel pointToUpdate, long idStudent) throws StudentNotFoundException;
	
	/**
	 * Récupère tous les points bonus/malus de tous les élèves
	 * 
	 * @return les points de tous les élèves
	 */
	public List<PointModel> getPoints();
}
