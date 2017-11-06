package fr.iagl.gamification.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Objet représentant le formulaire de la création d'une classe
 * 
 * @author Hélène Meyer
 *
 */
public class ClassForm {

	private int id;

	/**
	 * Nom d'une classe 
	 */
	@Size(min = 2, max = 30, message = "Le nom de la classe doit avoir entre {min} et {max} caractères.")
	@Pattern(regexp="^[-_a-zA-Z0-9]*$", message= "Le nom de la classe ne doit contenir que des caractères alphanumériques et - et _.")
	@NotNull(message = "Entrez un nom de classe")
	private String className;

	/**
	 * Getter du nom de la classe
	 * 
	 * @return le nom de la classe
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Setter du nom de la classe
	 * 
	 * @param className le nouveau nom de la classe
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
