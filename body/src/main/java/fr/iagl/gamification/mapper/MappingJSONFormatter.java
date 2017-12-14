package fr.iagl.gamification.mapper;

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
}
