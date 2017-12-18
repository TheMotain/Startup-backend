package fr.iagl.gamification.mapper.composite;

import java.util.HashMap;
import java.util.Map;

import fr.iagl.gamification.mapper.MappingJSONFormatter;

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
	public boolean createFormatter(String key, MappingJSONFormatter formatter) {
		if(objects.containsKey(key)) {
			return false;
		}
		objects.put(key, formatter);
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.iagl.gamification.mapper.MappingJSONFormatter#format(java.lang.Object)
	 */
	@Override
	public <E> Map<String, Object> format(E input) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<String, Object> output = new HashMap<>();
		// TODO object type
		for(Map.Entry<String, MappingJSONFormatter> formatter : objects.entrySet()) {
			output.put(formatter.getKey(), formatter.getValue().format(input));
		}
		return output;
	}
}
