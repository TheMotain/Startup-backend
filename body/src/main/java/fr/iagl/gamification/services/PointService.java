package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.entity.PointEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.PointModel;

/**
 * Service de gestion de point d'un élève
 * 
 * @author Hélène MEYER
 *
 */
public interface PointService {

	/**
	 * Récupère les points associés à un élève
	 * @param studentID ID de l'élève à chercher
	 * @return Le model contenant le détail des points
	 * @throws GamificationServiceException Est relevé si l'étudiant n'existe pas
	 */
	public PointModel getPoint(long studentID) throws GamificationServiceException;
	
	/**
	 * Mettre à jour un bonus ou un malus à un élève
	 * 
	 * @param pointToUpdate les points à modifier de l'élève
	 * @param idStudent l'identifiant de l'élève 
	 * @return l'ensemble des points de l'élève
	 * @throws GamificationServiceException 
	 */
	public PointModel updatePoint(PointModel pointToUpdate, long idStudent) throws GamificationServiceException;
	
	/**
	 * Récupère tous les points bonus/malus de tous les élèves
	 * 
	 * @return les points de tous les élèves
	 */
	public List<PointModel> getPoints();

	public void updateLevel(PointEntity point);
}
