package fr.iagl.gamification.validator;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Resultat du QCM envoyé par un élève
 *
 * @author Hélène MEYER
 *
 */
@ApiModel
public class ResultQcmForm implements AbstractForm{

	/**
	 * identifiant de l'élève
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@NotNull
	private Long idStudent;
	
	/**
	 * identifiant de la réponse
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@NotNull
	@NotEmpty
	private List<Long> idAnswer;

	/**
	 * Getter de l'attribut {@link ResultQcmForm#idStudent}
	 * @return idStudent
	 */
	public Long getIdStudent() {
		return idStudent;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmForm#idStudent}
	 * @param idStudent l'attribut {@link ResultQcmForm#idStudent} à setter
	 */
	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}

	/**
	 * Getter de l'attribut {@link ResultQcmForm#idAnswer}
	 * @return idAnswer
	 */
	public List<Long> getIdAnswer() {
		return idAnswer;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmForm#idAnswer}
	 * @param idAnswer l'attribut {@link ResultQcmForm#idAnswer} à setter
	 */
	public void setIdAnswer(List<Long> idAnswer) {
		this.idAnswer = idAnswer;
	}
	
	
}
