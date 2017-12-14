package fr.iagl.gamification.mapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import fr.iagl.gamification.mapper.composite.MappingJSONAttribute;
import fr.iagl.gamification.mapper.composite.MappingJSONObject;

/**
 * Charge l'ensemble des mappings
 * 
 * @author dalencourt
 *
 */
@Component
public class ModelParser extends DefaultHandler {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(ModelParser.class);

	/**
	 * Chemin vers le schéma du ModelMappingFile
	 */
	private static final String CLASSPATH_CONFIG_MODEL_MAPPING_XSD = "config/model-mapping.xsd";

	/**
	 * Chemin vers le fichier ModelMappingFile
	 */
	private static final String CLASSPATH_CONFIG_MODEL_MAPPING_XML = "config/model-mapping.xml";

	/**
	 * Liste des mappers à réaliser
	 */
	private Map<MappingEnum, MappingJSONFormatter> mappers;
	
	/**
	 * Mapper en lecture
	 */
	private String readingMapper;
	
	private MappingJSONFormatter editingFormatter;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch(ModelMappingXMLKeyEnum.evaluate(localName)) {
		case ROOT : break;
		case MAPPING :
			readingMapper = attributes.getValue("name");
			editingFormatter = new MappingJSONObject();
			mappers.put(MappingEnum.evaluate(readingMapper), editingFormatter);
			break;
		case JSON_ATTRIBUTE :
			MappingJSONAttribute attribute = new MappingJSONAttribute();
//			editingFormatter.
			break;
		case JSON_OBJECT :
			throw new NotImplementedException("SAX parser need to implement JSON_OBJECT element");
		case JSON_TYPE :
			throw new NotImplementedException("SAX parser need to implement JSON_TYPE element");
		case MAPPING_OBJECT :
			throw new NotImplementedException("SAX parser need to implement MAPPING_OBJECT element");
		default:
			throw new SAXException("Unknow element name : " + localName);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO
	}

	/**
	 * Initialise l'esemble des mappings chargé
	 * @throws SAXException SAX Exception renvoyée si impossible de créer un XMLReader
	 */
	@PostConstruct
	private void init() {
		LOGGER.info("Start Initialisation ModelParser");
		ClassPathResource modelMappingSchema = new ClassPathResource(CLASSPATH_CONFIG_MODEL_MAPPING_XSD);
		ClassPathResource modelMappingFile = new ClassPathResource(CLASSPATH_CONFIG_MODEL_MAPPING_XML);
		if(!modelMappingFile.exists() || ! modelMappingSchema.exists()) {
			throw new RuntimeException("ModelMappingFile not found at path : " + modelMappingFile.getPath() + 
					" Or ModelMappingSchema not found at path : " + modelMappingSchema.getPath());
		}
		mappers = new HashMap<>();
		readingMapper = null;
		LOGGER.info("Start parsing file");
		try {
			XMLReader saxReader = XMLReaderFactory.createXMLReader();
			saxReader.setContentHandler(this);
			saxReader.parse(modelMappingFile.getURI().getPath());
			} catch (SAXException | IOException genericException) {
			throw new RuntimeException("Impossible to instantiate an XMLReader or imposible to parse input file", genericException);
		}
		LOGGER.info("End Initialisation ModelParser");
	}

	/**
	 * Récupère le mapping correspondant à celui demandé
	 * 
	 * @return le mapping à réaliser
	 */
	public MappingJSONFormatter getMapper(MappingEnum mapping) {
		return mappers.get(mapping);
	}

	/**
	 * Enumération contenant toutes les clés utiles à la lecture du fichier xml
	 * 
	 * @author dalencourt
	 *
	 */
	protected enum ModelMappingXMLKeyEnum {
		ROOT("mappings"), 
		MAPPING("mapping"), 
		JSON_ATTRIBUTE("jsonAttribute"), 
		JSON_OBJECT("jsonObject"), 
		JSON_ARRAY("jsonArray"), 
		MAPPING_OBJECT("mappingObject"), 
		JSON_TYPE("jsonType"), 
		NAME_ATTRIBUTE("name"),
		OJECT_TYPE("objectType"), 
		TYPE_NUMBER("number"),
		TYPE_STRING("string"), 
		TYPE_OBJECT("object"), 
		TYPE_ARRAY("array");

		/**
		 * Valeur xml
		 */
		private final String key;

		/**
		 * Constructeur de l'enum
		 * 
		 * @param key
		 *            valeur de l'enum
		 */
		private ModelMappingXMLKeyEnum(String key) {
			this.key = key;
		}
		
		/**
		 * Récupère une énumération en fonction de sa valeur
		 * @param key Valeur à rechercher
		 * @return Enumération correspondante
		 * @throws IllegalArgumentException Est remontée si aucune énumération ne correspond
		 */
		public static ModelMappingXMLKeyEnum evaluate(String key) {
			for(ModelMappingXMLKeyEnum temp : values()) {
				if(temp.key.equals(key)) {
					return temp;
				}
			}
			throw new IllegalArgumentException("No enum found for value : " + key);
		}
	}
}
