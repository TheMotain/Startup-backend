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

	/**
	 * Récupère une énumération en fonction de sa valeur
	 * @param mapping Valeur à rechercher
	 * @return Enumération correspondante
	 * @throws IllegalArgumentException Est remontée si aucune énumération ne correspond
	 */
	public static MappingEnum evaluate(String mapping) {
		for(MappingEnum temp : values()) {
			if(temp.mapping.equals(mapping)) {
				return temp;
			}
		}
		throw new IllegalArgumentException("No enum found for value : " + mapping);
	}
}
