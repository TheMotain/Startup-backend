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

	public static final String QCM_PATH_ROOT = "${url.qcm}";

	/**
	 * Chemin pour la récupération des points pour un utilisateur spécifique
	 */
	public static final String POINTS_PATH_ROOT_WITH_USERID = "${url.point.userID}";
	
	public static final String QCM_RESULT_PATH_ROOT = "${url.resultQcm}";
	public static final String QCM_SEND_RESULT_ROOT = "${url.resultQcm.send}";

	/**
	 * Classe non instanciable
	 */
	private MappingConstant() {
	}
}
