package fr.iagl.gamification.entity.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.iagl.gamification.entity.InventaireEntity;
import fr.iagl.gamification.entity.PriceEntity;

/**
 * Définition de la clé composite
 * @author dalencourt
 *
 */
@Embeddable
public class InventairePK implements Serializable{
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5179448407549803896L;

	/**
	 * Id de l'élève
	 */
	@Column(name = "pupil", columnDefinition = "integer", nullable = false)
	private Long studentId;
	
	/**
	 * Référence de l'avatar acheté vers le table des prix
	 */
	@ManyToOne
	@JoinColumn(name = "avatar_id", nullable = false)
	private PriceEntity avatarRef;

	/**
	 * Coonstructeur par defaut
	 */
	public InventairePK() {
		avatarRef = new PriceEntity();
	}
	
	/**
	 * Constructeur avec parametres
	 * @param studentId id élève
	 * @param avatarRef avatar de référence
	 */
	public InventairePK(Long studentId, PriceEntity avatarRef) {
		this.studentId = studentId;
		this.avatarRef = avatarRef;
	}

	/**
	 * Getter de l'attribut {@link InventaireEntity.InventairePK#studentId}
	 * @return studentId
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * Setter de l'attribut {@link InventaireEntity.InventairePK#studentId}
	 * @param studentId l'attribut {@link InventaireEntity.InventairePK#studentId} à setter
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Getter de l'attribut {@link InventaireEntity.InventairePK#avatarRef}
	 * @return avatarRef
	 */
	public PriceEntity getAvatarRef() {
		return avatarRef;
	}

	/**
	 * Setter de l'attribut {@link InventaireEntity.InventairePK#avatarRef}
	 * @param avatarRef l'attribut {@link InventaireEntity.InventairePK#avatarRef} à setter
	 */
	public void setAvatarRef(PriceEntity avatarRef) {
		this.avatarRef = avatarRef;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatarRef == null) ? 0 : avatarRef.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventairePK other = (InventairePK) obj;
		if (avatarRef == null) {
			if (other.avatarRef != null)
				return false;
		} else if (!avatarRef.equals(other.avatarRef))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}
}