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
	private String lastName;
	
	/**
	 * Date de naissance
	 */
	private Date born;
	
	/**
	 * Classe
	 */
	private ClassModel classroom;

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
	 * Getter de l'attribut {@link StudentModel#lastName}
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter de l'attribut {@link StudentModel#lastName}
	 * @param lastName l'attribut {@link StudentModel#lastName} à setter
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	/**
	 * Getter de la classe
	 * @return la classe
	 */
	public ClassModel getClassroom() {
		return classroom;
	}

	/**
	 * Setter de la classe
	 * @param classroom la classe
	 */
	public void setClassroom(ClassModel classroom) {
		this.classroom = classroom;
	}
	
	
}
