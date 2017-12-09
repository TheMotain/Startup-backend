package fr.iagl.gamification.services;

import fr.iagl.gamification.entity.StudentEntity;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;

/**
 * Service pour réaliser des manipulations sur les avatars
 * @author dalencourt
 *
 */
public interface AvatarService {
	
	/**
	 * Récupère l'avatar pour un étudiant
	 * @param idStudent id de l'étudiant
	 * @return l'avatar
	 * @throws GamificationServiceException Est retourné si l'ID de l'étudiant est null
	 */
	public AvatarModel findAvatar(Long idStudent) throws GamificationServiceException;

	/**
	 * Modification de l'avatar pour un étudiant
	 * @param idStudent id de l'étudiant
	 * @param avatar nouvel avatar à setter
	 * @return le nouvel avatar 
	 * @throws GamificationServiceException Est retourné si l'étudiant n'existe pas
	 */
	public AvatarModel updateAvatar(Long idStudent, String avatar) throws GamificationServiceException;

	/**
	 * Créer un Model d'avatar par défaut et le persiste en base
	 * @param student étudiant pour lequel créé l'avatar
	 * @return L'avatar par défaut créé
	 */
	public AvatarModel createDefaultAvatar(StudentEntity student);
}
