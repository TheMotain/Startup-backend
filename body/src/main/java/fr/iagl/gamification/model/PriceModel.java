package fr.iagl.gamification.model;

/**
 * Model définissant le prix d'un avatar
 * @author dalencourt
 *
 */
public class PriceModel {

	/**
	 * Avatar Name
	 */
	private String avatar;
	
	/**
	 * avatar price
	 */
	private Integer price;

	/**
	 * Getter de l'attribut {@link PriceModel#avatar}
	 * @return avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * Setter de l'attribut {@link PriceModel#avatar}
	 * @param avatar l'attribut {@link PriceModel#avatar} à setter
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * Getter de l'attribut {@link PriceModel#price}
	 * @return price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * Setter de l'attribut {@link PriceModel#price}
	 * @param price l'attribut {@link PriceModel#price} à setter
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
}
