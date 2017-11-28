package fr.iagl.gamification.validator;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fr.iagl.gamification.constants.CodeError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Formulaire d'entrée pour la création d'un élève
 * @author ALEX
 *
 */
@ApiModel
public class StudentForm implements AbstractForm {
	
	/**
	 * identifiant de l'élève
	 */
	private Long id;
	
	/**
	 * Nom
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@Pattern(regexp = "^[A-Z].*", message = CodeError.ERROR_FIRST_NAME_NOT_START_WITH_MAJ)
	@NotNull(message = CodeError.ERROR_NULL_FIRST_NAME)
	private String firstName;
	
	/**
	 * Prénom
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@Pattern(regexp = "^[A-Z].*", message = CodeError.ERROR_LAST_NAME_NOT_START_WITH_MAJ)
	@NotNull(message = CodeError.ERROR_NULL_LAST_NAME)
	private String lastName;
	
	/**
	 * Date de naissance
	 */
	@ApiModelProperty(required=true)
	@NotNull(message = CodeError.ERROR_NULL_BORN_DATE)
	private Date born;
	
	/**
	 * id de la classe
	 */
	@NotNull
	private Long idClass;

	/**
	 * Getter de l'attribut {@link StudentForm#firstName}
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter de l'attribut {@link StudentForm#firstName}
	 * @param firstName l'attribut {@link StudentForm#firstName} à setter
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter de l'attribut {@link StudentForm#lastName}
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter de l'attribut {@link StudentForm#lastName}
	 * @param lastName l'attribut {@link StudentForm#lastName} à setter
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter de l'attribut {@link StudentForm#born}
	 * @return born
	 */
	public Date getBorn() {
		return born;
	}

	/**
	 * Setter de l'attribut {@link StudentForm#born}
	 * @param born l'attribut {@link StudentForm#born} à setter
	 */
	public void setBorn(Date born) {
		this.born = born;
	}

	/**
	 * Getter de l'attribut {@link StudentForm#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link StudentForm#id}
	 * @param id l'attribut {@link StudentForm#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link StudentForm#idClass}
	 * @return idClass
	 */
	public Long getIdClass() {
		return idClass;
	}

	/**
	 * Setter de l'attribut {@link StudentForm#idClass}
	 * @param idClass l'attribut {@link StudentForm#idClass} à setter
	 */
	public void setIdClass(Long idClass) {
		this.idClass = idClass;
	}
	
	
}
