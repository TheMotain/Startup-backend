package fr.iagl.gamification.object;

import java.util.List;

public class QuestionObject {
	
	private Long id;
	
	private String query;
	
	private int nbPoints;
	
	private List<AnswerObject> answers;

	/**
	 * Getter de l'attribut {@link QcmObject.QuestionObject#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmObject.QuestionObject#id}
	 * @param id l'attribut {@link QcmObject.QuestionObject#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmObject.QuestionObject#query}
	 * @return query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Setter de l'attribut {@link QcmObject.QuestionObject#query}
	 * @param query l'attribut {@link QcmObject.QuestionObject#query} à setter
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Getter de l'attribut {@link QcmObject.QuestionObject#nbPoints}
	 * @return nbPoints
	 */
	public int getNbPoints() {
		return nbPoints;
	}

	/**
	 * Setter de l'attribut {@link QcmObject.QuestionObject#nbPoints}
	 * @param nbPoints l'attribut {@link QcmObject.QuestionObject#nbPoints} à setter
	 */
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	/**
	 * Getter de l'attribut {@link QcmObject.QuestionObject#answers}
	 * @return answers
	 */
	public List<AnswerObject> getAnswers() {
		return answers;
	}

	/**
	 * Setter de l'attribut {@link QcmObject.QuestionObject#answers}
	 * @param answers l'attribut {@link QcmObject.QuestionObject#answers} à setter
	 */
	public void setAnswers(List<AnswerObject> answers) {
		this.answers = answers;
	}
}
