package fr.iagl.gamification.model;

/**
 *Modèle représentant une réponse possible du qcm
 *
 * @author Hélène MEYER
 *
 */
public class AnswerModel {

	/**
	 * identifiant de la réponse
	 */
	private Long id;
	
	/**
	 * choix
	 */
	private String choice;
	
	/**
	 * true si c'est la bonne réponse
	 */
	private boolean good;

	/**
	 * Getter de l'attribut {@link AnswerModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link AnswerModel#id}
	 * @param id l'attribut {@link AnswerModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link AnswerModel#choice}
	 * @return choice
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * Setter de l'attribut {@link AnswerModel#choice}
	 * @param choice l'attribut {@link AnswerModel#choice} à setter
	 */
	public void setChoice(String choice) {
		this.choice = choice;
	}

	/**
	 * Getter de l'attribut {@link AnswerModel#good}
	 * @return good
	 */
	public boolean isGood() {
		return good;
	}

	/**
	 * Setter de l'attribut {@link AnswerModel#good}
	 * @param good l'attribut {@link AnswerModel#good} à setter
	 */
	public void setGood(boolean good) {
		this.good = good;
	}
	
}
