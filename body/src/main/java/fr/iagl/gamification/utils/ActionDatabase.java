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
	
	private String action;
	
	ActionDatabase(String action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return this.action;
	}
}
