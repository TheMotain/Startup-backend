package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.exceptions.GamificationServiceException;

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
}
