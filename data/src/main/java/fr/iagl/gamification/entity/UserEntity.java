package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité décrivant un utilisateur en base de données
 * @author Nadia
 *
 */

@Entity
@Table(name = "user")
public class UserEntity implements Serializable{

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -5944480790573583812L;
	
	/**
	 * Identifiant unique de l'utilisateur
	 */
	@Id
	@Column(name ="id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Token unique pour l'utillisateur
	 */
	private String uuid;

	/**
	 * Prénom
	 */
	@Column( name ="firstname" , nullable = false , columnDefinition = "text")
	private String firstName;
	
	/**
	 * Nom
	 */
	@Column( name ="lastname" , nullable = false , columnDefinition = "text")
	private String lastName;
	
	/**
	 * email
	 */
	@Column( name ="email" , nullable = false , columnDefinition = "text")
	private String email;
	
	/**
	 * mot de passe
	 */
	@Column( name ="password" , nullable = false , columnDefinition = "text")
	private String password;

	/**
	 * Getter de l'attribut {@link UserEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#id}
	 * @param id l'attribut {@link UserEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link UserEntity#firstName}
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#firstName}
	 * @param firstName l'attribut {@link UserEntity#firstName} à setter
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter de l'attribut {@link UserEntity#lastName}
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#lastName}
	 * @param lastName l'attribut {@link UserEntity#lastName} à setter
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter de l'attribut {@link UserEntity#email}
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#email}
	 * @param email l'attribut {@link UserEntity#email} à setter
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter de l'attribut {@link UserEntity#password}
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#password}
	 * @param password l'attribut {@link UserEntity#password} à setter
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	
	
	
}
