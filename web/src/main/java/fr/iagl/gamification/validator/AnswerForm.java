package fr.iagl.gamification.validator;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import fr.iagl.gamification.constants.CodeError;

/**
 * Vérification des choix
 *
 * @author Hélène MEYER
 *
 */
public class AnswerForm implements AbstractForm {
	
	/**
	 * identifiant du choix
	 */
	private Long id;
	
	/**
	 * intitulé du choix
	 */
	@NotNull
	@Pattern(regexp="^[àáâãäåçèéêëìíîïðòóôõöùúûüýÿa-zA-Z0-9- _'?!?;:=/+*x]*$", message = CodeError.ERROR_STRING_PATTERN_QCM_CHOICE)
	private String choice;
	
	/**
	 * true si c'est la bonne réponse
	 */
	@NotNull
	private boolean good;

	/**
	 * Getter de l'attribut {@link QcmForm.AnswerForm#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmForm.AnswerForm#id}
	 * @param id l'attribut {@link QcmForm.AnswerForm#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmForm.AnswerForm#choice}
	 * @return choice
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * Setter de l'attribut {@link QcmForm.AnswerForm#choice}
	 * @param choice l'attribut {@link QcmForm.AnswerForm#choice} à setter
	 */
	public void setChoice(String choice) {
		this.choice = choice;
	}

	/**
	 * Getter de l'attribut {@link QcmForm.AnswerForm#good}
	 * @return good
	 */
	public boolean isGood() {
		return good;
	}

	/**
	 * Setter de l'attribut {@link QcmForm.AnswerForm#good}
	 * @param good l'attribut {@link QcmForm.AnswerForm#good} à setter
	 */
	public void setGood(boolean good) {
		this.good = good;
	}
	
	
}
