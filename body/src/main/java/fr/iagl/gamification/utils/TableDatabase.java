package fr.iagl.gamification.utils;

/**
 * Tables de la base de données
 * 
 * @author Hélène Meyer
 *
 */
public enum TableDatabase {
	MESSAGE("MESSAGE");
	
	/**
	 * Nom de la table en base de données 
	 */
	private String tableName;
	
	/**
	 * Constructeur
	 * @param tableName Valeur à setter
	 */
	private TableDatabase(String tableName) {
		this.tableName = tableName;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.tableName;
	}
}
