package fr.iagl.gamification.model;

import org.json.JSONObject;

public class PointModel {
	
	/**
	 * bonus de l'élève
	 */
	private long bonus;
	
	/**
	 * malus de l'élève
	 */
	private long malus;
	
	/**
	 * élève
	 */
	private StudentModel student;

	/**
	 * Constructeur standard
	 */
	public PointModel() {
		
	}
	
	/**
	 * Constructeur à partir d'un model JSON
	 * @param json modèle à parser
	 */
	public PointModel(JSONObject json) {
		bonus = json.getLong("bonus");
		malus = json.getLong("malus");
		student = new StudentModel();
		student.setId(json.getLong("pupil"));
	}

	/**
	 * Getter de l'attribut {@link PointModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link PointModel#id}
	 * @param id l'attribut {@link PointModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link PointModel#bonus}
	 * @return bonus
	 */
	public long getBonus() {
		return bonus;
	}

	/**
	 * Setter de l'attribut {@link PointModel#bonus}
	 * @param bonus l'attribut {@link PointModel#bonus} à setter
	 */
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	/**
	 * Getter de l'attribut {@link PointModel#malus}
	 * @return malus
	 */
	public long getMalus() {
		return malus;
	}

	/**
	 * Setter de l'attribut {@link PointModel#malus}
	 * @param malus l'attribut {@link PointModel#malus} à setter
	 */
	public void setMalus(long malus) {
		this.malus = malus;
	}

	/**
	 * Getter de l'attribut {@link PointModel#student}
	 * @return student
	 */
	public StudentModel getStudent() {
		return student;
	}

	/**
	 * Setter de l'attribut {@link PointModel#student}
	 * @param student l'attribut {@link PointModel#student} à setter
	 */
	public void setStudent(StudentModel student) {
		this.student = student;
	}
}
