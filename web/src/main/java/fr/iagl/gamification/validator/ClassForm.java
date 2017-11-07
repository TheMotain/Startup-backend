package fr.iagl.gamification.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import fr.iagl.gamification.constants.CodeError;

/**
 * Objet représentant le formulaire de la création d'une classe
 * 
 * @author Hélène Meyer
 *
 */
public class ClassForm extends AbstractForm{

	/**
	 * Nom d'une classe 
	 */
	@Size(min = 2, max = 30, message = CodeError.ERROR_SIZE_2_30_CLASSNAME)
	@Pattern(regexp="^[a-zA-Z0-9- _']*$", message= CodeError.ERROR_PATTERN_CLASSNAME)
	@NotNull(message = CodeError.ERROR_NULL_CLASSNAME)
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
	
}
