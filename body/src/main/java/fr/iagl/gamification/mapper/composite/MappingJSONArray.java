package fr.iagl.gamification.mapper.composite;

import java.util.ArrayList;
import java.util.List;

import fr.iagl.gamification.mapper.MappingJSONFormatter;

/**
 * Formatter contenant une liste de formatteur Object / List / Attribute
 * @author alex
 *
 */
public class MappingJSONArray<E extends MappingJSONAttribute> implements MappingJSONFormatter{

	/**
	 * Liste contenant la suite d'éléments.
	 * <b/>
	 * La liste est typée et ne peut contenir qu'un seul type déléments
	 */
	private List<E> list;

	/**
	 * Constructeur
	 */
	public MappingJSONArray() {
		list = new ArrayList<>();
	}
	
	/**
	 * Ajoute un nouvel élément à la liste
	 * @param key paramètre non utilisé
	 * @param formatter formatteur à ajouter à la liste
	 * @return toujours true
	 */
	@Override
	public boolean createFormatter(String key, MappingJSONFormatter formatter) {
		return false;
	}

}
