package fr.iagl.gamification.validator;

import javax.validation.constraints.NotNull;

import fr.iagl.gamification.constants.CodeError;

/**
 * Formulaire du lien entre la classe et l'élève
 * 
 * @author Hélène MEYER
 *
 */
public class LinkStudentClassForm implements AbstractForm {

	/**
	 * id de la classe
	 */
	@NotNull(message = CodeError.ERROR_NULL)
	private int idClass;
	
	/**
	 * id de l'élève
	 */
	@NotNull(message = CodeError.ERROR_NULL)
	private int idStudent;

	/**
	 * getter de l'id de la classe
	 * 
	 * @return l'id de la classe
	 */
	public int getIdClass() {
		return idClass;
	}

	/**
	 * setter de l'id de la classe
	 * 
	 * @param idClass l'id de la classe
	 */
	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}

	/**
	 * getter de l'id de l'élève
	 * 
	 * @return l'id de l'élève
	 */
	public int getIdStudent() {
		return idStudent;
	}

	/**
	 * setter de l'id de l'élève
	 * 
	 * @param idStudent id de l'élève
	 */
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	
}
