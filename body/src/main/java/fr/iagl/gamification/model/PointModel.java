package fr.iagl.gamification.model;

import org.json.JSONObject;

/**
 * Model décrivant les points Bonus Malus d'un étudiant
 * @author dalencourt
 *
 */
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
	
	private int level;
	
	private Long pointsToNextLevel;

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
		level = json.getInt("level");
		pointsToNextLevel = json.getLong("point_to_next_level");
		student = new StudentModel();
		student.setId(json.getLong("pupil"));
	}
	
	/**
	 * Getter de l'attribut {@link PointModel#level}
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Setter de l'attribut {@link PointModel#level}
	 * @param level l'attribut {@link PointModel#level} à setter
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Getter de l'attribut {@link PointModel#pointsToNextLevel}
	 * @return pointsToNextLevel
	 */
	public Long getPointsToNextLevel() {
		return pointsToNextLevel;
	}

	/**
	 * Setter de l'attribut {@link PointModel#pointsToNextLevel}
	 * @param pointsToNextLevel l'attribut {@link PointModel#pointsToNextLevel} à setter
	 */
	public void setPointsToNextLevel(Long pointsToNextLevel) {
		this.pointsToNextLevel = pointsToNextLevel;
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
