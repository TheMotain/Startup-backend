package fr.iagl.gamification.object;

/**
 * Objet pour l'IHM representant une classe 
 * 
 * @author Hélène MEYER
 *
 */
public class ClassObject {

	/**
	 * id de la classe
	 */
	private Long id;
	
	/**
	 * nom de la classe
	 */
	private String className;

	/**
	 * getter de l'id
	 * 
	 * @return l'id de la classe
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'id
	 * 
	 * @param id de la classe
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter du nom de la classe
	 * 
	 * @return le nom de la classe
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Setter du nom de la classe
	 * 
	 * @param className le nouveau nom de la classe
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
