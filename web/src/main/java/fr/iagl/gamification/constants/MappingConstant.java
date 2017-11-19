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
	public static final String POST_FORM_CLASS = "${url.class}";
	/**
	 * Chemin root pour la gestion d'un élève
	 */
	public static final String STUDENT_PATH_ROOT = "${url.student}";
	
	/**
	 * Shemin root pour la suppression d'un élève d'une classe
	 */
	public static final String POST_DELETE_STUDENT_CLASS = "deleteClass";
	
	/**
	 * Le mapping Constant ne peut pas être instancié
	 */
	private MappingConstant() {}
}
