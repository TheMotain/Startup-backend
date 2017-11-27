package fr.iagl.gamification.validator;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fr.iagl.gamification.constants.CodeError;

/**
 * Vérification des questions
 *
 * @author Hélène MEYER
 *
 */
public class QuestionForm implements AbstractForm {
	
	/**
	 * identifiant de la question
	 */
	private Long id;
	
	/**
	 * Question
	 */
	@NotNull
	@Pattern(regexp="^[àáâãäåçèéêëìíîïðòóôõöùúûüýÿa-zA-Z0-9- _'/!?;:+*x=]*$", message = CodeError.ERROR_STRING_PATTERN_QCM_QUERY)
	private String query;
	
	/**
	 * liste des choix
	 */
	@NotNull
	private List<AnswerForm> choices;
	
	/**
	 * nombre de points à gagner
	 */
	@NotNull
	@Min(0)
	private int nbPoints;

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
	 * Getter de l'attribut {@link QcmForm.QuestionForm#nbPoints}
	 * @return nbPoints
	 */
	public int getNbPoints() {
		return nbPoints;
	}

	/**
	 * Setter de l'attribut {@link QcmForm.QuestionForm#nbPoints}
	 * @param nbPoints l'attribut {@link QcmForm.QuestionForm#nbPoints} à setter
	 */
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
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
