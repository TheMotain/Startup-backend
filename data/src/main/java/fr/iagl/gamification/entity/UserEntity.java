package fr.iagl.gamification.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Entité décrivant un utilisateur en base de données
 * @author Nadia
 *
 */

@Entity
@Table(name = "users")
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
	@Column(name ="uuid", nullable = false)
	private String uuid;
	
	/**
	 * Prénom
	 */
	@Column( name ="firstName" , nullable = true , columnDefinition = "text")
	private String firstName;

	/**
	 * Nom
	 */
	@Column( name ="name" , nullable = false , columnDefinition = "text")
	private String name;
	
	/**
	 * Email
	 */
	@Column( name ="email" , nullable = false , columnDefinition = "text")
	private String email;
	
	/**
	 * mot de passe
	 */
	@Column( name ="password" , nullable = false , columnDefinition = "text")
	private String password;

	/**
	 * rôle de l'utilisateur
	 */
	@ManyToOne
	@JoinColumn(name="role", nullable=false)
	private RoleUserEntity role;
	
	@PrePersist
    public void prePersist() {
    	this.uuid = UUID.randomUUID().toString();
    }
	
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
	public String getName() {
		return name;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#firstName}
	 * @param firstName l'attribut {@link UserEntity#firstName} à setter
	 */
	public void setName(String name) {
		this.name = name;
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

	/**
	 * Getter de l'attribut {@link UserEntity#uuid}
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#uuid}
	 * @param uuid l'attribut {@link UserEntity#uuid} à setter
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter de l'attribut {@link UserEntity#role}
	 * @return role
	 */
	public RoleUserEntity getRole() {
		return role;
	}

	/**
	 * Setter de l'attribut {@link UserEntity#role}
	 * @param role l'attribut {@link UserEntity#role} à setter
	 */
	public void setRole(RoleUserEntity role) {
		this.role = role;
	}

	
}
