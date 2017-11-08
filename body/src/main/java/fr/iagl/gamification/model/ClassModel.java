package fr.iagl.gamification.model;

/**
 * Représente une classe
 * 
 * @author Hélène Meyer
 *
 */
public class ClassModel {

	/**
	 * id de la classe
	 */
	private Long id;
	/**
	 * Nom de la classe
	 */
	private String className;

	/**
	 * Getter du nom de la classe
	 * 
	 * @return
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Setter du nom de la classe
	 * 
	 * @param className nom d'une classe
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Getter de l'id de la classe
	 * 
	 * @return l'id de la classe
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'id de la classe
	 * 
	 * @param id de la classe
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
