package com.example.websocketdemo.controller;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class MessageEntity implements Serializable {
 
	private static final long serialVersionUID = -3009157732242241606L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 
	@Column(name = "name")
	private String name;
 
	@Column(name = "content")
	private String content;
 
	protected MessageEntity() {
	}
 
	public MessageEntity(String name, String content) {
		this.name = name;
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format("Message[id=%d, name='%s', content='%s']", id, name, content);
	}
}