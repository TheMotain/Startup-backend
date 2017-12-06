package fr.iagl.gamification.utils;

/**
 * Tables de la base de données
 * 
 * @author Hélène Meyer
 *
 */
public enum TableDatabase {
	POINT("POINT"), 
	QCM("QCM"), 
	RESULT_QCM("RESULT_QCM");
	
	private String tableName;
	
	TableDatabase(String tableName) {
		this.tableName = tableName;
	}
	
	@Override
	public String toString() {
		return this.tableName;
	}
}
