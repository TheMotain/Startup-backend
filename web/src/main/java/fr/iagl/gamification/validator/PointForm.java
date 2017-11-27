package fr.iagl.gamification.validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Formulaire de modification des points
 * 
 * @author Hélène MEYER
 *
 */
public class PointForm implements AbstractForm{

	/**
	 * id des points
	 */
	private Long id;
	
	/**
	 * id de l'élève
	 */
	@NotNull
	private long idStudent;
	
	/**
	 * point de bonus à ajouter
	 */
	@NotNull
	@Min(value=0)
	private long bonus;
	
	/**
	 * point de malus à ajouter
	 */
	@NotNull
	@Min(value=0)
	private long malus;

	/**
	 * Getter de l'attribut {@link PointForm#idStudent}
	 * @return idStudent
	 */
	public long getIdStudent() {
		return idStudent;
	}

	/**
	 * Setter de l'attribut {@link PointForm#idStudent}
	 * @param idStudent l'attribut {@link PointForm#idStudent} à setter
	 */
	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}

	/**
	 * Getter de l'attribut {@link PointForm#bonus}
	 * @return bonus
	 */
	public long getBonus() {
		return bonus;
	}

	/**
	 * Setter de l'attribut {@link PointForm#bonus}
	 * @param bonus l'attribut {@link PointForm#bonus} à setter
	 */
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	/**
	 * Getter de l'attribut {@link PointForm#malus}
	 * @return malus
	 */
	public long getMalus() {
		return malus;
	}

	/**
	 * Setter de l'attribut {@link PointForm#malus}
	 * @param malus l'attribut {@link PointForm#malus} à setter
	 */
	public void setMalus(long malus) {
		this.malus = malus;
	}

	/**
	 * Getter de l'attribut {@link PointForm#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link PointForm#id}
	 * @param id l'attribut {@link PointForm#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
