package fr.iagl.gamification.constants;

import org.springframework.context.annotation.PropertySource;

/**
 * Contient les messages d'erreurs
 * @author dalencourt
 *
 */
@PropertySource("classpath:config/bundle/messages.properties")
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
	
	/**
	 * La classe ne peut pas être instanciée
	 */
	private CodeError(){
		
	}
}
