package fr.iagl.gamification.mapper.composite;

import java.util.List;

import fr.iagl.gamification.mapper.MappingJSONFormatter;
import fr.iagl.gamification.utils.Tuple;

/**
 * Réalise le mapping d'un object java vers un objet json<br>
 * 
 * @author alex
 *
 */
public class MappingJSONAttribute implements MappingJSONFormatter{
	
	/**
	 * Nom de destination json
	 */
	private String jsonKey;
	
	/**
	 * Non prit compte si type json ARRAY ou OBJECT<br>
	 * Succession des chemins pour accéder à l'objet final
	 */
	private String[] objectPath;
	
	/**
	 * Non prit en  si type json différent de ARRAY ou OBJECT<br>
	 * Contient en attribut un mapper complexe 
	 */
	private MappingJSONFormatter complexAttribute;
	
	/**
	 * Tuple contenant en premier le type JSON de destination en deuxième le type Object source
	 */
	private Tuple<JSONTypeEnum,Class<?>> typeJsonObject;
	
	/**
	 * Constructeur
	 * @param complextype l'attribut est un attribut complex ou non
	 */
	public MappingJSONAttribute(Tuple<JSONTypeEnum, JSONTypeEnum> tuple) {
		this.typeJsonObject = typeJsonObject;
	}
	
	/**
	 * Ne fait rien retourne toujours true
	 * @param key clé json de l'attribut
	 * @param formatter inutilisé
	 * @return Toujours vrai
	 */
	@Override
	public boolean createFormatter(String key, MappingJSONFormatter formatter) {
		jsonKey = key;
		return true;
	}
	
	/**
	 * Enumération des types JSON possibles
	 * @author alex
	 *
	 */
	public enum JSONTypeEnum {
		NUMBER (Double.class),
		INTEGER (Integer.class),
		DOUBLE (Double.class),
		STRING (String.class),
		OBJECT (Object.class),
		ARRAY (List.class);
		
		/**
		 * Mapping du type JAVA
		 */
		private Class<?> typeJava;

		/**
		 * Constructeur de l'enumeration
		 * @param typeJava
		 */
		private JSONTypeEnum(Class<?> typeJava) {
			this.typeJava = typeJava;
		}
		
		/**
		 * Getter de l'attribut {@link MappingJSONAttribute.JSONTypeEnum#typeJava}
		 * @return typeJava
		 */
		public Class<?> getTypeJava() {
			return typeJava;
		}
	}
}
