package fr.iagl.gamification.model;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;

/**
 * Model représentant un QCM
 *
 * @author Hélène MEYER
 *
 */
public class QcmModel {

	/**
	 * identifiant du QCM
	 */
	private Long id;
	
	/**
	 * titre du QCM
	 */
	private String title;
	
	/**
	 * Instruction du QCM
	 */
	private String instruction;
	
	/**
	 * classe
	 */
	private ClassModel classroom;

	/**
	 * date
	 */
	private Date dateIns;
	
	/**
	 * Liste de toutes les questions
	 */
	private List<QuestionModel> questions;
	
	/**
	 * Getter de l'attribut {@link QcmModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmModel#id}
	 * @param id l'attribut {@link QcmModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmModel#title}
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter de l'attribut {@link QcmModel#title}
	 * @param title l'attribut {@link QcmModel#title} à setter
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter de l'attribut {@link QcmModel#instruction}
	 * @return instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * Setter de l'attribut {@link QcmModel#instruction}
	 * @param instruction l'attribut {@link QcmModel#instruction} à setter
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * Getter de l'attribut {@link QcmModel#classroom}
	 * @return classroom
	 */
	public ClassModel getClassroom() {
		return classroom;
	}

	/**
	 * Setter de l'attribut {@link QcmModel#classroom}
	 * @param classroom l'attribut {@link QcmModel#classroom} à setter
	 */
	public void setClassroom(ClassModel classroom) {
		this.classroom = classroom;
	}

	/**
	 * Getter de l'attribut {@link QcmModel#questions}
	 * @return questions
	 */
	public List<QuestionModel> getQuestions() {
		return questions;
	}

	/**
	 * Setter de l'attribut {@link QcmModel#questions}
	 * @param questions l'attribut {@link QcmModel#questions} à setter
	 */
	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}

	/**
	 * Getter de l'attribut {@link QcmModel#dateIns}
	 * @return dateIns
	 */
	public Date getDateIns() {
		return dateIns;
	}

	/**
	 * Setter de l'attribut {@link QcmModel#dateIns}
	 * @param dateIns l'attribut {@link QcmModel#dateIns} à setter
	 */
	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}
	
	
}
