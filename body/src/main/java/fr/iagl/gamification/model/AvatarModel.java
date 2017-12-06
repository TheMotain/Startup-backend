package fr.iagl.gamification.model;

/**
 * Model représentant un avatar
 * 
 * @author dalencourt
 *
 */
public class AvatarModel {
	/**
	 * Id de lélève
	 */
	private Long idStudent;
	
	/**
	 * Id de l'avatar
	 */
	private String avatar;

	/**
	 * Getter de l'attribut {@link AvatarModel#idStudent}
	 * @return idStudent
	 */
	public Long getIdStudent() {
		return idStudent;
	}

	/**
	 * Setter de l'attribut {@link AvatarModel#idStudent}
	 * @param idStudent l'attribut {@link AvatarModel#idStudent} à setter
	 */
	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
	}

	/**
	 * Getter de l'attribut {@link AvatarModel#avatar}
	 * @return avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Setter de l'attribut {@link AvatarModel#avatar}
	 * @param avatar l'attribut {@link AvatarModel#avatar} à setter
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
