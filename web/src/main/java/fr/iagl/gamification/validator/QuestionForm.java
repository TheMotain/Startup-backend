package fr.iagl.gamification.validator;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fr.iagl.gamification.constants.CodeError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Vérification des questions
 *
 * @author Hélène MEYER
 *
 */
@ApiModel
public class QuestionForm implements AbstractForm {
	
	/**
	 * identifiant de la question
	 */
	@ApiModelProperty(required=false, allowEmptyValue=true)
	private Long id;
	
	/**
	 * Question
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@NotNull
	@Pattern(regexp="^[àáâãäåçèéêëìíîïðòóôõöùúûüýÿa-zA-Z0-9- _'/!?;:+*x=]*$", message = CodeError.ERROR_STRING_PATTERN_QCM_QUERY)
	private String query;
	
	/**
	 * liste des choix
	 */
	@ApiModelProperty(required=true, allowEmptyValue=false)
	@NotNull
	private List<AnswerForm> choices;

	/**
	 * Getter de l'attribut {@link QcmForm.QuestionForm#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmForm.QuestionForm#id}
	 * @param id l'attribut {@link QcmForm.QuestionForm#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmForm.QuestionForm#choices}
	 * @return choices
	 */
	public List<AnswerForm> getChoices() {
		return choices;
	}

	/**
	 * Setter de l'attribut {@link QcmForm.QuestionForm#choices}
	 * @param choices l'attribut {@link QcmForm.QuestionForm#choices} à setter
	 */
	public void setChoices(List<AnswerForm> choices) {
		this.choices = choices;
	}

	/**
	 * Getter de l'attribut {@link QcmForm.QuestionForm#query}
	 * @return query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Setter de l'attribut {@link QcmForm.QuestionForm#query}
	 * @param query l'attribut {@link QcmForm.QuestionForm#query} à setter
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
