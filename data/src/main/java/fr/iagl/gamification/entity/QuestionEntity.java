package fr.iagl.gamification.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Représente une question au QCM
 *
 * @author Hélène MEYER
 *
 */
@Entity
@Table(name = "question")
public class QuestionEntity {

	/**
	 * Identifiant unique de la question
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Intitulé de la Question du QCM
	 */
	@Column(name = "query", nullable = false, columnDefinition = "text")
	private String query;
	
	/**
	 * QCM
	 */
	@ManyToOne
	@JoinColumn(name = "qcm", nullable = false)
	private QcmEntity qcm;
	
	/**
	 * Nombre de points à gagner sur la question
	 */
	@Column(name = "nb_points", nullable = false)
	private int nbPoints;
	
	/**
	 * Récupération de la liste des choix de réponse pour la question
	 */
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<AnswerEntity> answers;

	/**
	 * Getter de l'attribut {@link QuestionEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QuestionEntity#id}
	 * @param id l'attribut {@link QuestionEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QuestionEntity#query}
	 * @return query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Setter de l'attribut {@link QuestionEntity#query}
	 * @param query l'attribut {@link QuestionEntity#query} à setter
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * Getter de l'attribut {@link QuestionEntity#qcm}
	 * @return qcm
	 */
	public QcmEntity getQcm() {
		return qcm;
	}

	/**
	 * Setter de l'attribut {@link QuestionEntity#qcm}
	 * @param qcm l'attribut {@link QuestionEntity#qcm} à setter
	 */
	public void setQcm(QcmEntity qcm) {
		this.qcm = qcm;
	}

	/**
	 * Getter de l'attribut {@link QuestionEntity#answers}
	 * @return answers
	 */
	public List<AnswerEntity> getAnswers() {
		return answers;
	}

	/**
	 * Setter de l'attribut {@link QuestionEntity#answers}
	 * @param answers l'attribut {@link QuestionEntity#answers} à setter
	 */
	public void setAnswers(List<AnswerEntity> answers) {
		this.answers = answers;
	}

	/**
	 * Getter de l'attribut {@link QuestionEntity#nbPoints}
	 * @return nbPoints
	 */
	public int getNbPoints() {
		return nbPoints;
	}

	/**
	 * Setter de l'attribut {@link QuestionEntity#nbPoints}
	 * @param nbPoints l'attribut {@link QuestionEntity#nbPoints} à setter
	 */
	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}
	
	
}
