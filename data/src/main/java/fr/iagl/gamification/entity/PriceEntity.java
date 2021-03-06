/**
 * 
 */
package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité qui definit un avatar et son prix
 * 
 * @author dalencourt
 *
 */
@Entity
@Table(name = "avatar_price")
public class PriceEntity implements Serializable {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -2914270958163271814L;

	/**
	 * Nom de l'avatar
	 */
	@Id
	@Column(name = "id", columnDefinition = "text", nullable = false)
	private String avatar;
	
	/**
	 * Prix de l'avatar
	 */
	@Column(name = "price", columnDefinition = "integer", nullable = false)
	private Integer price;

	/**
	 * Getter de l'attribut {@link PriceEntity#avatar}
	 * @return avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Setter de l'attribut {@link PriceEntity#avatar}
	 * @param avatar l'attribut {@link PriceEntity#avatar} à setter
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Getter de l'attribut {@link PriceEntity#price}
	 * @return price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * Setter de l'attribut {@link PriceEntity#price}
	 * @param price l'attribut {@link PriceEntity#price} à setter
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
