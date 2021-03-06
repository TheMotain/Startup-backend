package fr.iagl.gamification.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
	 * Argent de l'élève
	 */
	@Column(name = "argent")
	private BigDecimal argent;
	
	/**
	 * Elève
	 */
	@Id @Column(name="pupil") Long id;
	
	@Column(name = "level")
	private int level;
	
	@Column(name="point_to_next_level")
	private Long pointsToNextLevel;

	/**
	 * Liaison JPA vers l'entité {@link StudentEntity}
	 */
    @MapsId 
    @OneToOne(mappedBy = "points")
	@JoinColumn(name = "pupil", nullable = false)
	private StudentEntity student;

    @PrePersist
    public void prePersist() {
    	argent = new BigDecimal(0);
    }
    
	/**
	 * Getter de l'attribut {@link PointEntity#level}
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Setter de l'attribut {@link PointEntity#level}
	 * @param level l'attribut {@link PointEntity#level} à setter
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Getter de l'attribut {@link PointEntity#pointsToNextLevel}
	 * @return pointsToNextLevel
	 */
	public Long getPointsToNextLevel() {
		return pointsToNextLevel;
	}

	/**
	 * Setter de l'attribut {@link PointEntity#pointsToNextLevel}
	 * @param pointsToNextLevel l'attribut {@link PointEntity#pointsToNextLevel} à setter
	 */
	public void setPointsToNextLevel(Long pointsToNextLevel) {
		this.pointsToNextLevel = pointsToNextLevel;
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

	/**
	 * Getter de l'attribut {@link PointEntity#argent}
	 * @return argent
	 */
	public BigDecimal getArgent() {
		return argent;
	}

	/**
	 * Setter de l'attribut {@link PointEntity#argent}
	 * @param argent l'attribut {@link PointEntity#argent} à setter
	 */
	public void setArgent(BigDecimal argent) {
		this.argent = argent;
	}
	
}
