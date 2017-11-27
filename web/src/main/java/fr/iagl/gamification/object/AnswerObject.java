package fr.iagl.gamification.object;

public class AnswerObject {
	
	private Long id;
	
	private String choice;
	
	private boolean good;

	/**
	 * Getter de l'attribut {@link QcmObject.AnswerObject#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmObject.AnswerObject#id}
	 * @param id l'attribut {@link QcmObject.AnswerObject#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmObject.AnswerObject#choice}
	 * @return choice
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * Setter de l'attribut {@link QcmObject.AnswerObject#choice}
	 * @param choice l'attribut {@link QcmObject.AnswerObject#choice} à setter
	 */
	public void setChoice(String choice) {
		this.choice = choice;
	}

	/**
	 * Getter de l'attribut {@link QcmObject.AnswerObject#good}
	 * @return good
	 */
	public boolean isGood() {
		return good;
	}

	/**
	 * Setter de l'attribut {@link QcmObject.AnswerObject#good}
	 * @param good l'attribut {@link QcmObject.AnswerObject#good} à setter
	 */
	public void setGood(boolean good) {
		this.good = good;
	}
	
	
}
