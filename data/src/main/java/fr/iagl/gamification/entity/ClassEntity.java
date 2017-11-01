package fr.iagl.gamification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité représentant une classe
 * 
 * @author Hélène Meyer
 *
 */
@Entity
@Table(name = "classroom")
public class ClassEntity {

	/**
	 * id de la classe
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 
	
	/**
	 * Nom de la classe
	 */
	@Column(name = "name")
	private String name;

	public ClassEntity() {
		
	}
	
	public ClassEntity(String name) {
		this.name = name;
	}

	/**
	 * Getter du nom de la classe
	 * 
	 * @return le nom de la classe
	 */
	public String getClassName() {
		return name;
	}

	/**
	 * Setter du nom de la classe
	 * 
	 * @param className le nouveau nom de la classe
	 */
	public void setClassName(String className) {
		this.name = className;
	}
	
	
}
