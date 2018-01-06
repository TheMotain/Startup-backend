package fr.iagl.gamification.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fr.iagl.gamification.constants.CodeError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Formulaire d'entrée pour la création d'un professeur
 *
 * @author Hélène MEYER
 */
@ApiModel
public class TeacherForm implements AbstractForm {
	
	/**
	 * Nom
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@Pattern(regexp = "^[àáâãäåçèéêëìíîïðòóôõöùúûüýÿa-zA-Z0-9- _'?!?;:=/+*x]*$", message = CodeError.ERROR_NAME_NOT_CONFORM)
	@NotNull(message = CodeError.ERROR_NULL_NAME)
	private String name;
	
	/**
	 * Email
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[a-zA-Z]{2,3}$", message = CodeError.ERROR_MAIL_NOT_CONFORM)
	@NotNull(message = CodeError.ERROR_NULL_EMAIL)
	private String email;
	
	/**
	 * Mot de passe
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@Pattern(regexp = "^[àáâãäåçèéêëìíîïðòóôõöùúûüýÿa-zA-Z0-9- _'?!?;:=/+*x]*$", message = CodeError.ERROR_MDP_NOT_CONFORM)
	@NotNull(message = CodeError.ERROR_NULL_MDP)
	private String password;

	/**
	 * Getter de l'attribut {@link TeacherForm#name}
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter de l'attribut {@link TeacherForm#name}
	 * @param name l'attribut {@link TeacherForm#name} à setter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de l'attribut {@link TeacherForm#email}
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter de l'attribut {@link TeacherForm#email}
	 * @param email l'attribut {@link TeacherForm#email} à setter
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter de l'attribut {@link TeacherForm#password}
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter de l'attribut {@link TeacherForm#password}
	 * @param password l'attribut {@link TeacherForm#password} à setter
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
