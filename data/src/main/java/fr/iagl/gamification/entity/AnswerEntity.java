package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Représente une possibilité de réponse au QCM
 *
 * @author Hélène MEYER
 *
 */
@Entity
@Table(name = "answer")
public class AnswerEntity implements Serializable{

	/**
	 * Identifiant unique de la réponse
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Intitulé d'un choix d'une question
	 */
	@Column(name = "choice", nullable = false, columnDefinition = "text")
	private String choice;
	
	/**
	 * Intitulé d'un choix d'une question
	 */
	@Column(name = "good", columnDefinition = "boolean")
	private boolean good;
	
	/**
	 * Question
	 */
	@ManyToOne
	@JoinColumn(name = "question", nullable = true)
	private QuestionEntity question;

	/**
	 * Getter de l'attribut {@link AnswerEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link AnswerEntity#id}
	 * @param id l'attribut {@link AnswerEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link AnswerEntity#choice}
	 * @return choice
	 */
	public String getChoice() {
		return choice;
	}

	/**
	 * Setter de l'attribut {@link AnswerEntity#choice}
	 * @param choice l'attribut {@link AnswerEntity#choice} à setter
	 */
	public void setChoice(String choice) {
		this.choice = choice;
	}

	/**
	 * Getter de l'attribut {@link AnswerEntity#good}
	 * @return good
	 */
	public boolean isGood() {
		return good;
	}

	/**
	 * Setter de l'attribut {@link AnswerEntity#good}
	 * @param good l'attribut {@link AnswerEntity#good} à setter
	 */
	public void setGood(boolean good) {
		this.good = good;
	}

	/**
	 * Getter de l'attribut {@link AnswerEntity#question}
	 * @return question
	 */
	public QuestionEntity getQuestion() {
		return question;
	}

	/**
	 * Setter de l'attribut {@link AnswerEntity#question}
	 * @param question l'attribut {@link AnswerEntity#question} à setter
	 */
	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}
	
	
}
