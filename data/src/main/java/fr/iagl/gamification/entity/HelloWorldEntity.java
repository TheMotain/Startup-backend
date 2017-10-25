package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "helloworld")
public class HelloWorldEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9185711706047100344L;
	
	@Id
	@Column(name="hello")
	private String hello;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
	
}
