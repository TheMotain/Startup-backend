package fr.iagl.gamification.mapper;

/**
 * Liste des mappings configuré 
 * 
 * @author dalencourt
 *
 */
public enum MappingEnum {
	POINTMODEL("PointMapping");
	
	/**
	 * Nom du mapping
	 */
	private String mapping;
	
	/**
	 * Constructeur de l'enum
	 * @param mapping
	 */
	private MappingEnum(String mapping) {
		this.mapping = mapping;
	}
}
