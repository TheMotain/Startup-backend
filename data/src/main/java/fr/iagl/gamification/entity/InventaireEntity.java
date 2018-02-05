/**
 * 
 */
package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.iagl.gamification.entity.pk.InventairePK;

/**
 * Mapping table des avatars achetés
 * 
 * @author dalencourt
 *
 */
@Entity
@Table(name = "bougth_avatar")
public class InventaireEntity implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -166502276787086743L;
	
	/**
	 * Clé composite
	 */
	@EmbeddedId
	private InventairePK id;
	
	@OneToOne
	@JoinColumn(name = "avatar_id", nullable = false, insertable = false, updatable = false)
	private AvatarEntity selectAvatar;
	
	/**
	 * Constructeur par defaut
	 */
	public InventaireEntity() {
		id = new InventairePK();
	}
	
	/**
	 * Constructeur avec pk en paramètre
	 * @param id pk à utiliser
	 */
	public InventaireEntity(InventairePK id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link InventaireEntity#id}
	 * @return id
	 */
	public InventairePK getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link InventaireEntity#id}
	 * @param id l'attribut {@link InventaireEntity#id} à setter
	 */
	public void setId(InventairePK id) {
		this.id = id;
	}
}
