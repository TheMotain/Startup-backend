package fr.iagl.gamification.mapper.composite;

import java.util.HashMap;
import java.util.Map;

/**
 * Formatter contenant une map d'objets / attributs / listes
 * @author dalencourt
 *
 */
public class MappingJSONObject implements MappingJSONFormatter {
	/**
	 * Contient les élément de l'objet à générer
	 */
	private Map<String, MappingJSONFormatter> objects;
	
	/**
	 * Constructeur
	 */
	public MappingJSONObject() {
		objects = new HashMap<>();
	}
	
	@Override
	public boolean createFormatter(MappingJSONFormatter formatter) {
		return false;
	}

}
