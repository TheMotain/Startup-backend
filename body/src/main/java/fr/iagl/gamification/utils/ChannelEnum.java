package fr.iagl.gamification.utils;
 
public enum ChannelEnum {
  NOTIFICATION_POINT ("notification","point"), 
  NOTIFICATION_QCM ("notification", "qcm"), 
  NOTIFICATION_RESULT_QCM ("notification", "result_qcm");
  
  public static final String ROOT_CHANNEL = "/channel";
  public static final String NOTIFICATION = "notification";
  
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
   * Retourne l'URL de broadcast pour un ID particuliet
   * @param id ID à inclure dans l'URL
   * @return L'URL généré
   */
  public String getFullChannelURLWithID(String id) {
    return String.format("%s/%s", getFullChannelURL(), id);
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