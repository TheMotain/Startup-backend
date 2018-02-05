package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.entity.InventaireEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.InventaireModel;

/**
 * Service pour la récupération des éléments de l'inventaire avatar
 * @author dalencourt
 *
 */
public interface InventaireService {

	/**
	 * Récupère la liste de tous les avatars acheté par l'élève en paramètre
	 * @param id Identifiant de l'utilisateur à filter
	 * @return liste des avatars qui ont été acheté
	 * @throws GamificationServiceException Est retournée si l'élève en paramètre n'est pas connu ou null
	 */
	List<String> getAllBougthAvatar(Long id) throws GamificationServiceException;

	/**
	 * Enregistre l'avatar par défaut pour l'utilisateur en paramètre
	 * @param id Utilisateur pour lequel enregistrer l'avatar
	 * @return l'avatar créé
	 */
	InventaireEntity createDefaultAvatar(Long id);

	/**
	 * Récupère un inventaire si sa clé student / avatar existe
	 * @param avatar avatar à rechercher
	 * @param idStudent student à filtrer
	 * @return L'inventaire si il existe
	 */
	InventaireEntity findAvatarForStudent(String avatar, Long idStudent);
	
	/**
	 * Permet d'acheter un avatar
	 * @param avatar Avatar à acheter
	 * @param idStudent Etudiant achetant l'avatar 
	 * @return Avatar qui a été acheté, null si l'achat est impossible (manque d'argent ou déjà acheté)
	 * @throws GamificationServiceException si l'avatar / student
	 */
	InventaireModel buyAvatar(String avatar, Long idStudent) throws GamificationServiceException;
}
