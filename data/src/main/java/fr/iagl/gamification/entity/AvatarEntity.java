/**
 * 
 */
package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * Définit un Avatar en base de donnée
 * @author dalencourt
 *
 */
public class AvatarEntity implements Serializable{
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3687290340508664728L;

	/**
	 * Id de l'avatar
	 */
	@Id
	@Column(name = "pupil", nullable = false)
	private Long id;
	
	/**
	 * Nom de l'avatar
	 */
	@Column(name = "avatar_name", nullable = false)
	private String pseudo;
	
	/**
	 * Avatar à afficher
	 */
	@Column(name = "avatar_id", nullable = false)
	private String avatar;
	
	/**
	 * Liaison JPA vers l'entité {@link StudentEntity}
	 */
    @MapsId 
    @OneToOne(mappedBy = "points")
	@JoinColumn(name = "pupil", nullable = false)
	private StudentEntity student;

	/**
	 * Getter de l'attribut {@link AvatarEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link AvatarEntity#id}
	 * @param id l'attribut {@link AvatarEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link AvatarEntity#pseudo}
	 * @return pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Setter de l'attribut {@link AvatarEntity#pseudo}
	 * @param pseudo l'attribut {@link AvatarEntity#pseudo} à setter
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * Getter de l'attribut {@link AvatarEntity#avatar}
	 * @return avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Setter de l'attribut {@link AvatarEntity#avatar}
	 * @param avatar l'attribut {@link AvatarEntity#avatar} à setter
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Getter de l'attribut {@link AvatarEntity#student}
	 * @return student
	 */
	public StudentEntity getStudent() {
		return student;
	}

	/**
	 * Setter de l'attribut {@link AvatarEntity#student}
	 * @param student l'attribut {@link AvatarEntity#student} à setter
	 */
	public void setStudent(StudentEntity student) {
		this.student = student;
	}
}
