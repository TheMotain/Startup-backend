package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité qui représente un message dans la base de donnée
 * 
 * @author Hélène Meyer
 *
 */
@Entity
@Table(name = "message")
public class MessageEntity implements Serializable {
 
	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	/**
	 * Nom de celui qui a envoyé le message
	 */
	@Column(name = "sender")
	private String sender;
 
	/**
	 * Contenu du message
	 */
	@Column(name = "content")
	private String content;
 
	public MessageEntity() {
	}
 
	public MessageEntity(String name, String content) {
		this.sender = name;
		this.content = content;
	}

	
	
	/**
	 * Getter de l'attribut {@link MessageEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link MessageEntity#id}
	 * @param id l'attribut {@link MessageEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link MessageEntity#sender}
	 * @return sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Setter de l'attribut {@link MessageEntity#sender}
	 * @param sender l'attribut {@link MessageEntity#sender} à setter
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Getter de l'attribut {@link MessageEntity#content}
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Setter de l'attribut {@link MessageEntity#content}
	 * @param content l'attribut {@link MessageEntity#content} à setter
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format("Message[id=%d, name='%s', content='%s']", id, sender, content);
	}
}