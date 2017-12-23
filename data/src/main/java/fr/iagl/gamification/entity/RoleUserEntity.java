package fr.iagl.gamification.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role_user")
public class RoleUserEntity implements Serializable  {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = 8652841504295936791L;

	/**
	 * Identifiant unique d'un rôle de l'utilisateur
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Rôle
	 */
	@Column(name = "role", nullable = false, columnDefinition = "text")
	private String role;
	
	/**
	 * Récupération de la liste des utilisateurs
	 */
	@OneToMany(mappedBy = "role")
	private List<UserEntity> users;

	/**
	 * Getter de l'attribut {@link RoleUserEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link RoleUserEntity#id}
	 * @param id l'attribut {@link RoleUserEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link RoleUserEntity#role}
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Setter de l'attribut {@link RoleUserEntity#role}
	 * @param role l'attribut {@link RoleUserEntity#role} à setter
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
