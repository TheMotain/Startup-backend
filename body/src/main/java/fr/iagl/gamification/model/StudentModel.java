package fr.iagl.gamification.model;

import java.util.Date;

/**
 * Model représentant un élève
 * @author ALEX
 *
 */
public class StudentModel {

	/**
	 * ID unique de l'élève
	 */
	private Long id;
	
	/**
	 * Prénom
	 */
	private String firstName;
	
	/**
	 * Nom
	 */
	private String lastDame;
	
	/**
	 * Date de naissance
	 */
	private Date born;

	/**
	 * Getter de l'attribut {@link StudentModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link StudentModel#id}
	 * @param id l'attribut {@link StudentModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link StudentModel#firstName}
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter de l'attribut {@link StudentModel#firstName}
	 * @param firstName l'attribut {@link StudentModel#firstName} à setter
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter de l'attribut {@link StudentModel#lastDame}
	 * @return lastDame
	 */
	public String getLastDame() {
		return lastDame;
	}

	/**
	 * Setter de l'attribut {@link StudentModel#lastDame}
	 * @param lastDame l'attribut {@link StudentModel#lastDame} à setter
	 */
	public void setLastDame(String lastDame) {
		this.lastDame = lastDame;
	}

	/**
	 * Getter de l'attribut {@link StudentModel#born}
	 * @return born
	 */
	public Date getBorn() {
		return born;
	}

	/**
	 * Setter de l'attribut {@link StudentModel#born}
	 * @param born l'attribut {@link StudentModel#born} à setter
	 */
	public void setBorn(Date born) {
		this.born = born;
	}
}
