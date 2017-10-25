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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 
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
 
	protected MessageEntity() {
	}
 
	public MessageEntity(String name, String content) {
		this.sender = name;
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format("Message[id=%d, name='%s', content='%s']", id, sender, content);
	}
}