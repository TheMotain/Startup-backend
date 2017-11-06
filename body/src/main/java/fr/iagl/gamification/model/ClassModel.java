package fr.iagl.gamification.model;

/**
 * Représente une classe
 * 
 * @author Hélène Meyer
 *
 */
public class ClassModel {

	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
