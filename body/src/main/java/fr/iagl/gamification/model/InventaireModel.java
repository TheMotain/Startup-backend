package fr.iagl.gamification.model;

/**
 * Modèle inventaire pour les avatar qui ont été acheté
 * @author dalencourt
 *
 */
public class InventaireModel {

	/**
	 * Id de l'étudiant propriétaire
	 */
	private Long studentID;
	
	/**
	 * Id de l'avatar disponible
	 */
	private String avatar;

	/**
	 * Getter de l'attribut {@link InventaireModel#studentID}
	 * @return studentID
	 */
	public Long getStudentID() {
		return studentID;
	}

	/**
	 * Setter de l'attribut {@link InventaireModel#studentID}
	 * @param studentID l'attribut {@link InventaireModel#studentID} à setter
	 */
	public void setStudentID(Long studentID) {
		this.studentID = studentID;
	}

	/**
	 * Getter de l'attribut {@link InventaireModel#avatar}
	 * @return avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Setter de l'attribut {@link InventaireModel#avatar}
	 * @param avatar l'attribut {@link InventaireModel#avatar} à setter
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
