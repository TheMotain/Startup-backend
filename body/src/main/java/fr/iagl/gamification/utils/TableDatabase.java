package fr.iagl.gamification.utils;

import fr.iagl.gamification.entity.MessageEntity;
import fr.iagl.gamification.model.PointModel;

/**
 * Tables de la base de données
 * 
 * @author Hélène Meyer
 *
 */
public enum TableDatabase {
	/**
	 * Table message {@link MessageEntity}
	 */
	MESSAGE("MESSAGE"),
	/**
	 * Table point {@link PointModel}
	 */
	POINT("POINT")
	;
	
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
