package fr.iagl.gamification.constants;

/**
 * Contient les messages d'erreurs
 * @author dalencourt
 *
 */
public class CodeError {

	public static final String ERROR_EXISTS_CLASS = "La classe existe déjà.";
	public static final String ERROR_SIZE_2_30_CLASSNAME = "Le nom de la classe doit avoir entre {min} et {max} caractères.";
	public static final String ERROR_PATTERN_CLASSNAME = "Le nom de la classe ne doit contenir que des caractères alphanumériques et - et _.";
	public static final String ERROR_NULL_CLASSNAME = "Entrez un nom de classe";
	public static final String ERROR_FIRST_NAME_NOT_START_WITH_MAJ = "Le prénom doit commencer par une majuscule";
	public static final String ERROR_LAST_NAME_NOT_START_WITH_MAJ = "Le nom doit commencer par une majuscule";
	public static final String ERROR_NULL_FIRST_NAME = "Le prenom ne peut pas être null";
	public static final String ERROR_NULL_LAST_NAME = "Le nom ne peut pas être null";
	public static final String ERROR_NULL_BORN_DATE = "La date de naissance ne peut pas être null";
	public static final String ERROR_NULL = "L'identifiant ne peut pas être nul";
	public static final String ERROR_NOT_EXISTS_STUDENT = "l'élève est inexistant ";
	public static final String ERROR_NOT_EXISTS_CLASS = "l'élève n'est attribué à aucune classe";
	public static final String SAVE_FAIL = "L'enregistrement n'a pas été effectué.";
	public static final String ERROR_NOT_EXISTS_CLASSROOM = "La classe n'existe pas";
	public static final String ERROR_CLASS_ALREADY_EXISTS = "L'élève est déjà rattaché à une classe";
	public static final String INVALID_STUDENT_POINT = "Les points de l'élève sont invalides";
	public static final String ERROR_STRING_PATTERN = "Le nom ne doit contenir que des caratères alphanumériques";
	public static final String ERROR_STRING_PATTERN_QCM_TITRE = "Le titre du QCM ne doit contenir que des caratères alphanumériques";
	public static final String ERROR_STRING_PATTERN_QCM_INSTRUCTION = "Les instructions du QCM ne doit contenir que des caratères alphanumériques";
	public static final String ERROR_STRING_PATTERN_QCM_QUERY = "L'intitulé de la question du QCM ne doit contenir que des caratères alphanumériques";
	public static final String ERROR_STRING_PATTERN_QCM_CHOICE = "Le choix du QCM ne doit contenir que des caratères alphanumériques";
	public static final String ERROR_NULL_NAME = "Veuillez remplir le nom";
	public static final String ERROR_NAME_NOT_CONFORM = "Le nom n'est pas conforme";
	public static final String ERROR_MAIL_NOT_CONFORM = "Le mail n'est pas conforme";
	public static final String ERROR_PASSWORD_NOT_CONFORM = "Le mot de passe n'est pas conforme";
	public static final String ERROR_NULL_EMAIL = "Veuillez remplir le mail";
	public static final String ERROR_NULL_PASSWORD = "Veuillez remplir le mot de passe";
	public static final String ERROR_EMAIL_ALREADY_EXISTS = "Le mail existe déjà";
	public static final String ERROR_CRYPTAGE_PASSWORD = "Le cryptage du mot de passe ne s'est pas effectué correctement";

	
	/**
	 * La classe ne peut pas être instanciée
	 */
	private CodeError(){
		
	}
}
