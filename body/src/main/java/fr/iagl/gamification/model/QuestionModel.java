package fr.iagl.gamification.model;

import java.util.List;

/**
 * Modèle représentant une question du QCM
 *
 * @author Hélène MEYER
 *
 */
public class QuestionModel {

	/**
	 * identifiant de la question
	 */
	private Long id;
	
	/**
	 * question
	 */
	private String query;
	
	/**
	 * liste de toutes les réponses possibles
	 */
	private List<AnswerModel> answers;
	
	/**
	 * nombre de points à gagner
	 */
	private int nbPoints;

	/**
	 * Getter de l'attribut {@link QuestionModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QuestionModel#id}
	 * @param id l'attribut {@link QuestionModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QuestionModel#query}
	 * @return query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Setter de l'attribut {@link QuestionModel#query}
	 * @param query l'attribut {@link QuestionModel#query} à setter
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Getter de l'attribut {@link QuestionModel#answers}
	 * @return answers
	 */
	public List<AnswerModel> getAnswers() {
		return answers;
	}

	/**
	 * Setter de l'attribut {@link QuestionModel#answers}
	 * @param answers l'attribut {@link QuestionModel#answers} à setter
	 */
	public void setAnswers(List<AnswerModel> answers) {
		this.answers = answers;
	}

	/**
	 * Getter de l'attribut {@link QuestionModel#nbPoints}
	 * @return nbPoints
	 */
	public int getNbPoints() {
		return nbPoints;
	}

	/**
	 * Setter de l'attribut {@link QuestionModel#nbPoints}
	 * @param nbPoints l'attribut {@link QuestionModel#nbPoints} à setter
	 */
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}
	
	
}
