package fr.iagl.gamification.validator;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fr.iagl.gamification.constants.CodeError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Qcm à envoyer/modifier
 *
 * @author Hélène MEYER
 *
 */
@ApiModel
public class QcmForm implements AbstractForm{

	/**
	 * id de la classe
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	private long idClass;
	
	/**
	 * id du QCM
	 */
	@ApiModelProperty(required=false, allowEmptyValue=true)
	private Long id;
	
	/**
	 * titre du QCM
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@NotNull
	@Pattern(regexp="^[àáâãäåçèéêëìíîïðòóôõöùúûüýÿa-zA-Z0-9- _'?!.+=*/;:]*$", message = CodeError.ERROR_STRING_PATTERN_QCM_TITRE)
	private String title;
	
	/**
	 * instruction du QCM
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@NotNull
	@Pattern(regexp="^[àáâãäåçèéêëìíîïðòóôõöùúûüýÿa-zA-Z0-9- _'?!/+*x?;:]*$", message = CodeError.ERROR_STRING_PATTERN_QCM_INSTRUCTION)
	private String instruction;
	
	/**
	 * Ensemble des questions du QCM
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@NotNull
	private List<QuestionForm> questions;

	/**
	 * Getter de l'attribut {@link QcmForm#idClass}
	 * @return idClass
	 */
	public long getIdClass() {
		return idClass;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#idClass}
	 * @param idClass l'attribut {@link QcmForm#idClass} à setter
	 */
	public void setIdClass(long idClass) {
		this.idClass = idClass;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#id}
	 * @param id l'attribut {@link QcmForm#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#title}
	 * @return titre
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#title}
	 * @param titre l'attribut {@link QcmForm#title} à setter
	 */
	public void setTitle(String titre) {
		this.title = titre;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#instruction}
	 * @return instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#instruction}
	 * @param instruction l'attribut {@link QcmForm#instruction} à setter
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#questions}
	 * @return questions
	 */
	public List<QuestionForm> getQuestions() {
		return questions;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#questions}
	 * @param questions l'attribut {@link QcmForm#questions} à setter
	 */
	public void setQuestions(List<QuestionForm> questions) {
		this.questions = questions;
	}
	
}
