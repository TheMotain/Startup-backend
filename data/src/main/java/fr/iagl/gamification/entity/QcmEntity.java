package fr.iagl.gamification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@Column(name = "instruction", nullable = false, columnDefinition = "text")
	private String instruction;
	
	/**
	 * Classe
	 */
	@ManyToOne
	@JoinColumn(name = "classroom", nullable = true)
	private ClassEntity classroom;

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
	
	
}
