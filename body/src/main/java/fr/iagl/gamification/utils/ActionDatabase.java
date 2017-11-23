package fr.iagl.gamification.utils;

/**
 * Actions de la base de données
 * 
 * @author Hélène Meyer
 *
 */
public enum ActionDatabase {
	INSERT("INSERT"),
	UPDATE("UPDATE"), 
	DELETE("DELETE");
	
	/**
	 * Action réalisé en base de données
	 */
	private String action;
	
	/**
	 * Constructeur
	 * @param action Valeur à setter
	 */
	private ActionDatabase(String action) {
		this.action = action;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.action;
	}
}
