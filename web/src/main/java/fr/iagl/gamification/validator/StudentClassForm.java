package fr.iagl.gamification.validator;

import javax.validation.constraints.NotNull;

import fr.iagl.gamification.constants.CodeError;

public class StudentClassForm {

	/**
	 * id de l'élève
	 */
	@NotNull(message = CodeError.ERROR_NULL)
	private long idStudent;
	
	/**
	 * id de la classe
	 */
	@NotNull(message = CodeError.ERROR_NULL)
	private long idClass;

	/**
	 * getter de l'id de l'élève
	 * @return l'id de l'élève
	 */
	public long getIdStudent() {
		return idStudent;
	}

	/**
	 * Setter de l'id de l'élève
	 * @param idStudent id de l'élève
	 */
	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}

	/**
	 * getter de l'id de la classe
	 * @return id de la classe
	 */
	public long getIdClass() {
		return idClass;
	}

	/**
	 * Setter de l'id de la classe
	 * @param idClass id de la classe
	 */
	public void setIdClass(long idClass) {
		this.idClass = idClass;
	}
	
	
	
}
