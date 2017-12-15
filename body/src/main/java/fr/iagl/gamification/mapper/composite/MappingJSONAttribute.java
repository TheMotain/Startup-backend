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
	 * Succession des chemins pour accéder à l'objet final
	 */
	private String[] objectPath;
	
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
	 * Getter de l'attribut {@link MappingJSONAttribute#objectPath}
	 * @return objectPath
	 */
	public String[] getObjectPath() {
		return objectPath;
	}

	/**
	 * Setter de l'attribut {@link MappingJSONAttribute#objectPath}
	 * @param objectPath l'attribut {@link MappingJSONAttribute#objectPath} à setter
	 */
	public void setObjectPath(String[] objectPath) {
		this.objectPath = objectPath;
	}

	/**
	 * Getter de l'attribut {@link MappingJSONAttribute#typeJsonObject}
	 * @return typeJsonObject
	 */
	public Tuple<JSONTypeEnum, Class<?>> getTypeJsonObject() {
		return typeJsonObject;
	}

	/**
	 * Setter de l'attribut {@link MappingJSONAttribute#typeJsonObject}
	 * @param typeJsonObject l'attribut {@link MappingJSONAttribute#typeJsonObject} à setter
	 */
	public void setTypeJsonObject(Tuple<JSONTypeEnum, Class<?>> typeJsonObject) {
		this.typeJsonObject = typeJsonObject;
	}

	/**
	 * Getter de l'attribut {@link MappingJSONAttribute#jsonKey}
	 * @return jsonKey
	 */
	public String getJsonKey() {
		return jsonKey;
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
