package fr.iagl.gamification.constants;

/**
 * Constantes définissant les clé URL des mappings rest
 * 
 * @author Hélène Meyer
 *
 */
public class MappingConstant {

	/**
	 * Requete post pour la création d'une classe
	 */
	public static final String CLASS_PATH_ROOT = "${url.class}";
	
	/**
	 * Chemin root pour la gestion d'un élève
	 */
	public static final String STUDENT_PATH_ROOT = "${url.student}";
	/**
	 * Chemin pour l'ajout d'une classe à un élève
	 */
	public static final String POST_ADD_CLASS_TO_STUDENT = "${url.student.addClass}";
	/**
	 * Shemin root pour la suppression d'un élève d'une classe
	 */
	public static final String POST_DELETE_STUDENT_CLASS = "${url.student.deleteClass}";
	
	/**
	 * Chemin pour la modification des points d'un élève
	 */
	public static final String POINTS_PATH_ROOT = "${url.point}";
	/**
	 * Chemin pour la récupération des points pour un utilisateur spécifique
	 */
	public static final String POINTS_PATH_ROOT_WITH_USERID = "${url.point.userID}";

	/**
	 * Chemin principal pour la manipulation des QCM
	 */
	public static final String QCM_PATH_ROOT = "${url.qcm}";
	/**
	 * Chemin pour récupérer la liste des QCM par classe
	 */
	public static final String QCM_FILTER_CLASSROM = "${url.qcm.filter.classroom}";
	/**
	 * Permet de récupérer les résultats d'un QCM donné / Envoyer les réponses d'un élève
	 */
	public static final String QCM_RESULT_PATH_ROOT = "${url.resultQcm}";
	/**
	 * Submit la création d'un QCM
	 */
	public static final String QCM_SEND_RESULT_ROOT = "${url.resultQcm.send}";

	/**
	 * URL pour les manipulations d'avatar
	 */
	public static final String AVATAR_CRUD = "${url.avatar.crud}";

	/**
	 * URL pour récupération des avatars avec leur prix
	 */
	public static final String AVATAR_PRICE = "${url.avatar.price}";

	/**
	 * URL pour la récupération de tous les avatars acheté par un élève
	 */
	public static final String AVATAR_BOUGTH = "${url.avatar.bougth}";

	/**
	 * URL pour l'achat d'un avatar
	 */
	public static final String AVATAR_BUY = "${url.avatar.buy}";
	
	/**
	 * URL de connexion d'un étudiant
	 */
	public static final String STUDENT_PATH_CONNECT = "${url.student.connect}";

	/**
	 * Chemin pour la récupération et l'ajout de professeur
	 */
	public static final String TEACHER_PATH_ROOT = "${url.teacher}";

	/**
	 * Chemin pour la connexion du professeur
	 */
	public static final String CONNECT_TEACHER_PATH = "${url.teacher.connect}";

	/**
	 * Chemin pour la déconnexion
	 */
	public static final String LOGOUT_PATH = "${url.logout}";
	
	/**
	 * Classe non instanciable
	 */
	private MappingConstant() {
	}
}
