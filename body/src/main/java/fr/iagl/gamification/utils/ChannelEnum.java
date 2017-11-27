package fr.iagl.gamification.utils;

public enum ChannelEnum {
	NOTIFICATION_POINT ("notification","point");
	
	public static final String ROOT_CHANNEL = "/channel";
	
	private String domain;
	
	private String channel;
	
	private ChannelEnum(String domain, String channel) {
		this.domain = domain;
		this.channel = channel;
	}
	
	/**
	 * Retourne l'URL de broadcast
	 * @return L'URL généré
	 */
	public String getFullChannelURL() {
		return String.format("%s/%s/%s", ROOT_CHANNEL, domain, channel);
	}
	
	/**
	 * Retourne l'URL de broadcast pour un utilisateur
	 * @param userID ID utilisateur à inclure dans l'URL
	 * @return L'URL généré
	 */
	public String getFullChannelURLUserID(String userID) {
		return String.format("%s/%s", getFullChannelURL(), userID);
	}
	
	/**
	 * Getter de l'attribut {@link ChannelEnum#domain}
	 * @return domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Getter de l'attribut {@link ChannelEnum#channel}
	 * @return channel
	 */
	public String getChannel() {
		return channel;
	}

}
