package fr.iagl.gamification.object;

import java.util.Date;

public class StudentObject {
	
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
	private String lastName;
	
	/**
	 * Date de naissance
	 */
	private Date born;
	
	/**
	 * Classe
	 */
	private Long classroom;
	
	/**
	 * Points bonus
	 */
	private long bonus;
	
	/**
	 * Points malus;
	 */
	private long malus;

	/**
	 * Getter de l'attribut {@link StudentObject#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link StudentObject#id}
	 * @param id l'attribut {@link StudentObject#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link StudentObject#firstName}
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter de l'attribut {@link StudentObject#firstName}
	 * @param firstName l'attribut {@link StudentObject#firstName} à setter
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter de l'attribut {@link StudentObject#lastName}
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter de l'attribut {@link StudentObject#lastName}
	 * @param lastName l'attribut {@link StudentObject#lastName} à setter
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter de l'attribut {@link StudentObject#born}
	 * @return born
	 */
	public Date getBorn() {
		return born;
	}

	/**
	 * Setter de l'attribut {@link StudentObject#born}
	 * @param born l'attribut {@link StudentObject#born} à setter
	 */
	public void setBorn(Date born) {
		this.born = born;
	}

	/**
	 * Getter de l'attribut {@link StudentObject#classroom}
	 * @return classroom
	 */
	public Long getClassroom() {
		return classroom;
	}

	/**
	 * Setter de l'attribut {@link StudentObject#classroom}
	 * @param classroom l'attribut {@link StudentObject#classroom} à setter
	 */
	public void setClassroom(Long classroom) {
		this.classroom = classroom;
	}

	/**
	 * Getter de l'attribut {@link StudentObject#bonus}
	 * @return bonus
	 */
	public long getBonus() {
		return bonus;
	}

	/**
	 * Setter de l'attribut {@link StudentObject#bonus}
	 * @param bonus l'attribut {@link StudentObject#bonus} à setter
	 */
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	/**
	 * Getter de l'attribut {@link StudentObject#malus}
	 * @return malus
	 */
	public long getMalus() {
		return malus;
	}

	/**
	 * Setter de l'attribut {@link StudentObject#malus}
	 * @param malus l'attribut {@link StudentObject#malus} à setter
	 */
	public void setMalus(long malus) {
		this.malus = malus;
	}

}
