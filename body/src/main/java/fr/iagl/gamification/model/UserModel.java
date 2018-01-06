package fr.iagl.gamification.model;

/**
 * Classe représentant un utilisateur
 *
 * @author Hélène MEYER
 *
 */
public class UserModel {

	/**
	 * id de l'utilisateur
	 */
	protected Long id;
	
	/**
	 * uuid unique de l'utilisateur
	 */
	protected String uuid;
	
	/**
	 * Nom de l'utilisateur
	 */
	protected String name;
	
	/**
	 * email de l'utilisateur
	 */
	protected String email;
	
	/**
	 * mot de passe crypté de l'utilisateur
	 */
	protected String password;

	/**
	 * Getter de l'attribut {@link UserModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link UserModel#id}
	 * @param id l'attribut {@link UserModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link UserModel#uuid}
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter de l'attribut {@link UserModel#uuid}
	 * @param uuid l'attribut {@link UserModel#uuid} à setter
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter de l'attribut {@link UserModel#name}
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter de l'attribut {@link UserModel#name}
	 * @param name l'attribut {@link UserModel#name} à setter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de l'attribut {@link UserModel#email}
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter de l'attribut {@link UserModel#email}
	 * @param email l'attribut {@link UserModel#email} à setter
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter de l'attribut {@link UserModel#password}
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter de l'attribut {@link UserModel#password}
	 * @param password l'attribut {@link UserModel#password} à setter
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
