package fr.iagl.gamification.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entité définissant un élève
 * @author ALEX
 *
 */
@Entity
@Table(name = "pupil")
public class StudentEntity {
	
	/**
	 * Id unique. Nécessaire car il est possible d'avoir des élèves homonymes
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Prénom
	 */
	@Column(name = "firstName")
	private String firstName; 
	
	/**
	 * Nom
	 */
	@Column(name = "lastName")
	private String lastName;

	/**
	 * Date de naissance
	 */
	@Column(name = "borndate")
	private Date born;
	
	/**
	 * Classe associée à l'élève
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "classroom")
	private ClassEntity associateClass;

	/**
	 * Getter de l'attribut {@link StudentEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#id}
	 * @param id l'attribut {@link StudentEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#firstName}
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#firstName}
	 * @param firstName l'attribut {@link StudentEntity#firstName} à setter
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#lastName}
	 * @return lastName
	 */
	public String getListName() {
		return lastName;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#lastName}
	 * @param lastName l'attribut {@link StudentEntity#lastName} à setter
	 */
	public void setListName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#born}
	 * @return born
	 */
	public Date getBorn() {
		return born;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#born}
	 * @param born l'attribut {@link StudentEntity#born} à setter
	 */
	public void setBorn(Date born) {
		this.born = born;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#associateClass}
	 * @return associateClass
	 */
	public ClassEntity getAssociateClass() {
		return associateClass;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#associateClass}
	 * @param associateClass l'attribut {@link StudentEntity#associateClass} à setter
	 */
	public void setAssociateClass(ClassEntity associateClass) {
		this.associateClass = associateClass;
	}
}
