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
	 * Chemin pour récupérer les élèves n'ayant pas de classe
	 */
	public static final String GET_STUDENTS_WITHOUT_CLASS = "${url.studentwithoutclass}";
	/**
	 * Chemin pour l'ajout d'une classe à un élève
	 */
	public static final String POST_ADD_CLASS = "${url.class.addClass}";
	/**
	 * Chemin pour la modification des points d'un élève
	 */
	public static final String POINTS_PATH_ROOT = "${url.point}";
	
	/**
	 * Le mapping Constant ne peut pas être instancié
	 */
	private MappingConstant() {}
}
