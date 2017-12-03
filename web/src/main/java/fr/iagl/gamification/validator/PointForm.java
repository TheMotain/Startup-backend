package fr.iagl.gamification.validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Formulaire de modification des points
 * 
 * @author Hélène MEYER
 *
 */
@ApiModel
public class PointForm implements AbstractForm{
	
	/**
	 * id de l'élève
	 */
	@ApiModelProperty(required=true)
	@NotNull
	private long idStudent;
	
	/**
	 * point de bonus à ajouter
	 */
	@ApiModelProperty(required=true)
	@NotNull
	@Min(value=0)
	private long bonus;
	
	/**
	 * point de malus à ajouter
	 */
	@ApiModelProperty(required=true)
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
	
}
