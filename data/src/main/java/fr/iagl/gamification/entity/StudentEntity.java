package fr.iagl.gamification.entity;

import java.io.Serializable;
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
 * Entité décrivant un élève en base de données
 * @author ALEX
 *
 */
@Entity
@Table(name = "pupil")
public class StudentEntity implements Serializable {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = 8652841504295936751L;

	/**
	 * Identifiant unique de l'élève
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Prénom
	 */
	@Column(name = "firsname", nullable = false, columnDefinition = "text")
	private String firstName;
	
	/**
	 * Nom
	 */
	@Column(name = "lastname", nullable = false, columnDefinition = "text")
	private String lastName;
	
	/**
	 * date de naissance
	 */
	@Column(name = "bornedate", nullable = false, columnDefinition = "date")
	private Date bornDate;
	
	@ManyToOne
	@JoinColumn(name = "classroom", nullable = true)
	private ClassEntity classroom;

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
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#lastName}
	 * @param lastName l'attribut {@link StudentEntity#lastName} à setter
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#bornDate}
	 * @return bornDate
	 */
	public Date getBornDate() {
		return bornDate;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#bornDate}
	 * @param bornDate l'attribut {@link StudentEntity#bornDate} à setter
	 */
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#classroom}
	 * @return classroom
	 */
	public ClassEntity getClassroom() {
		return classroom;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#classroom}
	 * @param classroom l'attribut {@link StudentEntity#classroom} à setter
	 */
	public void setClassroom(ClassEntity classroom) {
		this.classroom = classroom;
	}
}
