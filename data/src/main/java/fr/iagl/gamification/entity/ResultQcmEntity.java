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
 * Représentation de la table result qcm
 *
 * @author Hélène MEYER
 *
 */
@Entity
@Table(name = "result_qcm")
public class ResultQcmEntity implements Serializable {

	/**
	 * id de la table result qcm
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * eleve qui a envoyé le qcm
	 */
	@ManyToOne
	@JoinColumn(name = "pupil", nullable = true)
	private StudentEntity student;

	/**
	 * réponse de l'élève
	 */
	@ManyToOne
	@JoinColumn(name = "answer", nullable = true)
	private AnswerEntity answer;

	/**
	 * Getter de l'attribut {@link ResultQcmEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmEntity#id}
	 * @param id l'attribut {@link ResultQcmEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link ResultQcmEntity#student}
	 * @return student
	 */
	public StudentEntity getStudent() {
		return student;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmEntity#student}
	 * @param student l'attribut {@link ResultQcmEntity#student} à setter
	 */
	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	/**
	 * Getter de l'attribut {@link ResultQcmEntity#answer}
	 * @return answer
	 */
	public AnswerEntity getAnswer() {
		return answer;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmEntity#answer}
	 * @param answer l'attribut {@link ResultQcmEntity#answer} à setter
	 */
	public void setAnswer(AnswerEntity answer) {
		this.answer = answer;
	}
	
	
}
