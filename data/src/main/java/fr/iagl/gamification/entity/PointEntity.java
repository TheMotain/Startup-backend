package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Représente les points de l'élève 
 * 
 * @author Hélène MEYER
 *
 */
@Entity
@Table(name = "point")
public class PointEntity implements Serializable {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -3650567266024965280L;

	/**
	 * id des points de l'élève
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Bonus de l'élève
	 */
	@Column(name = "bonus")
	private long bonus;
	
	/**
	 * Malus de l'élève
	 */
	@Column(name = "malus")
	private long malus;
	
	/**
	 * Elève
	 */
	@OneToOne
	@JoinColumn(name = "pupil", nullable = true)
	private StudentEntity student;

	/**
	 * Getter de l'attribut {@link PointEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link PointEntity#id}
	 * @param id l'attribut {@link PointEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link PointEntity#bonus}
	 * @return bonus
	 */
	public long getBonus() {
		return bonus;
	}

	/**
	 * Setter de l'attribut {@link PointEntity#bonus}
	 * @param bonus l'attribut {@link PointEntity#bonus} à setter
	 */
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	/**
	 * Getter de l'attribut {@link PointEntity#malus}
	 * @return malus
	 */
	public long getMalus() {
		return malus;
	}

	/**
	 * Setter de l'attribut {@link PointEntity#malus}
	 * @param malus l'attribut {@link PointEntity#malus} à setter
	 */
	public void setMalus(long malus) {
		this.malus = malus;
	}

	/**
	 * Getter de l'attribut {@link PointEntity#student}
	 * @return student
	 */
	public StudentEntity getStudent() {
		return student;
	}

	/**
	 * Setter de l'attribut {@link PointEntity#student}
	 * @param student l'attribut {@link PointEntity#student} à setter
	 */
	public void setStudent(StudentEntity student) {
		this.student = student;
	}
	
}
