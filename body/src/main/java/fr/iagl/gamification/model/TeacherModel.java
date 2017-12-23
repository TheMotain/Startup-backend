package fr.iagl.gamification.model;

public class TeacherModel {

	/**
	 * id du professeur
	 */
	private Long id;
	
	private String uuid;
	/**
	 * Nom du professeur
	 */
	private String name;
	
	private String email;
	
	private String password;

	/**
	 * Getter de l'attribut {@link TeacherModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link TeacherModel#id}
	 * @param id l'attribut {@link TeacherModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link TeacherModel#uuid}
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Setter de l'attribut {@link TeacherModel#uuid}
	 * @param uuid l'attribut {@link TeacherModel#uuid} à setter
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Getter de l'attribut {@link TeacherModel#name}
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter de l'attribut {@link TeacherModel#name}
	 * @param name l'attribut {@link TeacherModel#name} à setter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter de l'attribut {@link TeacherModel#email}
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter de l'attribut {@link TeacherModel#email}
	 * @param email l'attribut {@link TeacherModel#email} à setter
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter de l'attribut {@link TeacherModel#password}
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter de l'attribut {@link TeacherModel#password}
	 * @param password l'attribut {@link TeacherModel#password} à setter
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
