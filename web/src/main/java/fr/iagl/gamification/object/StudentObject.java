package fr.iagl.gamification.object;

import java.util.Date;

/**
 * Objet pour l'IHM représentant un élève
 * 
 * @author Hélène MEYER
 *
 */
public class StudentObject {

	/**
	 * id de l'élève
	 */
	private Long id;
	
	/**
	 * prénom de l'élève
	 */
	private String firstName;
	
	/**
	 * nom de l'élève
	 */
	private String lastName;
	
	/**
	 * date de naissance
	 */
	private Date born;
	
	/**
	 * identifiant de la classe
	 */
	private Long idClass;

	/**
	 * Getter de l'identifiant
	 * 
	 * @return l'identifiant de l'élève
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'identifiant
	 * 
	 * @param id identifiant
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter du prénom
	 * 
	 * @return le prénom de l'élève
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter du prénom
	 * 
	 * @param firstName le prénom de l'élève
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter du nom de l'élève
	 * 
	 * @return le nom de l'élève
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter du nom de famille
	 * 
	 * @param lastName le nouveau nom de famille
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter de la date de naissance
	 * 
	 * @return la date de naissance
	 */
	public Date getBorn() {
		return born;
	}

	/**
	 * Setter de la date de naissance
	 * 
	 * @param born la date de naissance
	 */
	public void setBorn(Date born) {
		this.born = born;
	}

	/**
	 * Getter de l'id de la classe
	 * 
	 * @return l'identifiant de la classe
	 */
	public Long getIdClass() {
		return idClass;
	}

	/**
	 * Setter de l'identifiant de la classe
	 * 
	 * @param idClass identifiant de la classe
	 */
	public void setIdClass(Long idClass) {
		this.idClass = idClass;
	}
}
