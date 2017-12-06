/**
 * 
 */
package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

/**
 * Définit un Avatar en base de donnée
 * @author dalencourt
 *
 */
@Entity
@Table(name = "avatar")
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
	private Long idStudent;
	
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
     * Actions à réaliser avant persistance des données
     */
    @PrePersist
    private void prePersist() {
    	if(null == avatar) {
    		avatar = "default";
    	}
    }
    
	/**
	 * Getter de l'attribut {@link AvatarEntity#idStudent}
	 * @return idStudent
	 */
	public Long getIdStudent() {
		return idStudent;
	}

	/**
	 * Setter de l'attribut {@link AvatarEntity#idStudent}
	 * @param idStudent l'attribut {@link AvatarEntity#idStudent} à setter
	 */
	public void setIdStudent(Long idStudent) {
		this.idStudent = idStudent;
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
