package fr.iagl.gamification.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Objet de la base de donnée représentant un QCM
 *
 * @author Hélène MEYER
 *
 */
@Entity
@Table(name = "qcm")
public class QcmEntity {
	
	/**
	 * Identifiant unique du qcm
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * titre du QCM
	 */
	@Column(name = "title", nullable = false, columnDefinition = "text")
	private String title;
	
	/**
	 * Instruction du QCM
	 */
	@Column(name = "instruction", columnDefinition = "text")
	private String instruction;
	
	/**
	 * Classe
	 */
	@ManyToOne
	@JoinColumn(name = "classroom", nullable = true)
	private ClassEntity classroom;
	
	/**
	 * date
	 */
	@JoinColumn(name = "date_ins")
	private Date dateIns;
	
	/**
	 * Récupération de la liste des questions pour le qcm
	 */
	@OneToMany(mappedBy = "qcm")
	private List<QuestionEntity> questions;

	/**
	 * Getter de l'attribut {@link QcmEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmEntity#id}
	 * @param id l'attribut {@link QcmEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmEntity#title}
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter de l'attribut {@link QcmEntity#title}
	 * @param title l'attribut {@link QcmEntity#title} à setter
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter de l'attribut {@link QcmEntity#instruction}
	 * @return instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * Setter de l'attribut {@link QcmEntity#instruction}
	 * @param instruction l'attribut {@link QcmEntity#instruction} à setter
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * Getter de l'attribut {@link QcmEntity#classroom}
	 * @return classroom
	 */
	public ClassEntity getClassroom() {
		return classroom;
	}

	/**
	 * Setter de l'attribut {@link QcmEntity#classroom}
	 * @param classroom l'attribut {@link QcmEntity#classroom} à setter
	 */
	public void setClassroom(ClassEntity classroom) {
		this.classroom = classroom;
	}

	/**
	 * Getter de l'attribut {@link QcmEntity#questions}
	 * @return questions
	 */
	public List<QuestionEntity> getQuestions() {
		return questions;
	}

	/**
	 * Setter de l'attribut {@link QcmEntity#questions}
	 * @param questions l'attribut {@link QcmEntity#questions} à setter
	 */
	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}

	/**
	 * Getter de l'attribut {@link QcmEntity#dateIns}
	 * @return dateIns
	 */
	public Date getDateIns() {
		return dateIns;
	}

	/**
	 * Setter de l'attribut {@link QcmEntity#dateIns}
	 * @param dateIns l'attribut {@link QcmEntity#dateIns} à setter
	 */
	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}
	
	
}
