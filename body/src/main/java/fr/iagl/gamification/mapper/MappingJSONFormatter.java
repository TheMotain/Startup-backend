package fr.iagl.gamification.mapper;

import java.util.List;
import java.util.Map;

/**
 * Design pattern Composite
 * <br>
 * <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Composite_UML_class_diagram_fr.svg/480px-Composite_UML_class_diagram_fr.svg.png"/>
 * <br>
 * Permet de réaliser un parser objet -> json en mode built-in
 * 
 * @author dalencourt
 *
 */
public interface MappingJSONFormatter {
	
	/**
	 * Créer un nouveau formateur. Reprendre le schéma du design pattern Component
	 * @param key clé du mapping à créer
	 * @param formatter Formatter a créer
	 * @return true ou false si le formateur peut être créé
	 */
	public boolean createFormatter(String key, MappingJSONFormatter formatter);

	/**
	 * Applique le formatter à l'input
	 * @param input objet à formatter
	 * @return json formatté
	 * @throws SecurityException est remontée si erreur de sécurité
	 * @throws NoSuchFieldException est remontée si il n'y a pas de correspondance
	 * @throws IllegalAccessException est remonté si l'attribut n'a pas la visibilité requise 
	 * @throws IllegalArgumentException est remonté si l'attribut est une sous instance de l'objet 
	 */
	public <E> Object format(E input) throws NoSuchFieldException, SecurityException, IllegalArgumentException , IllegalAccessException;
}
