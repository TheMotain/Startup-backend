package fr.iagl.gamification.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import fr.iagl.gamification.mapper.composite.MappingJSONAttribute;
import fr.iagl.gamification.mapper.composite.MappingJSONObject;
import fr.iagl.gamification.mapper.composite.MappingJSONAttribute.JSONTypeEnum;
import fr.iagl.gamification.utils.Tuple;

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

	/**
	 * Formatteur en edition
	 */
	private MappingJSONFormatter editingFormatter;

	/**
	 * Pile de formatteurs encours d'édition
	 */
	private List<MappingJSONFormatter> formatterStack;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (ModelMappingXMLKeyEnum.evaluate(localName)) {
		case ROOT:
			break;
		case MAPPING:
			if(editingFormatter != null) {
				throw new SAXException("XML file is invalide last mapper is not closed");
			}
			readingMapper = attributes.getValue(ModelMappingXMLKeyEnum.NAME.key);
			editingFormatter = new MappingJSONObject();
			mappers.put(MappingEnum.evaluate(readingMapper), editingFormatter);
			break;
		case JSON_ATTRIBUTE:
			if(editingFormatter == null){
				throw new SAXException("XML file is invalid try to create a json attribute out of mapper")
			}
			if(editingFormatter instanceof MappingJSONAttribute) {
				throw new SAXException("XML file is invalid try to create a json attribute inside one other");
			}
			MappingJSONAttribute currentAttribute = new MappingJSONAttribute(
					new Tuple<>(JSONTypeEnum.valueOf(attributes.getValue(ModelMappingXMLKeyEnum.JSON_TYPE.key)),
							StringUtils.isNotBlank(attributes.getValue(ModelMappingXMLKeyEnum.OJECT_TYPE.key))
									? JSONTypeEnum.valueOf(ModelMappingXMLKeyEnum.OJECT_TYPE.key)
									: null));
			editingFormatter.createFormatter(attributes.getValue(ModelMappingXMLKeyEnum.NAME.key), currentAttribute);
			formatterStack.add(0, editingFormatter);
			editingFormatter = currentAttribute;
			break;
		case JSON_OBJECT:
			if(editingFormatter == null){
				throw new SAXException("XML file is invalid try to create a json object out of mapper")
			}
			throw new NotImplementedException("SAX parser need to implement JSON_TYPE element");
		case MAPPING_OBJECT:
			if(editingFormatter == null){
				throw new SAXException("XML file is invalid try to create a json array out of mapper")
			}
			throw new NotImplementedException("SAX parser need to implement MAPPING_OBJECT element");
		default:
			throw new SAXException("Unknow element name : " + localName);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (ModelMappingXMLKeyEnum.evaluate(localName)) {
		case ROOT:
			if(!formatterStack.isEmpty() || editingFormatter != null) {
				throw new SAXException("XML file is invalid one mapper is not closed");
			}
			break;
		case MAPPING:
			if(!formatterStack.isEmpty()) {
				throw new SAXException("XML file is invalid one Attribute is not closed");
			}
			if(!(editingFormatter instanceof ModelJSONObject)){
				throw new SAXException("XML file is invalid try to close a none mapper element")
			}
			editingFormatter = null;
			break;
		case JSON_ATTRIBUTE:
			if(formatterStack.isEmpty()) {
				throw new SAXException("XML file is invalid try to close a attribute out of mapper");
			}
			if(!(editingFormatter instanceof MappingJSONAttribute)) {
				throw new SAXException("XML file is invalid try to close a none json attribute");
			}
			editingFormatter = formatterStack.remove(0);
			break;
		case JSON_OBJECT:
			throw new NotImplementedException("SAX parser need to implement JSON_TYPE element");
		case MAPPING_OBJECT:
			throw new NotImplementedException("SAX parser need to implement MAPPING_OBJECT element");
		default:
			throw new SAXException("Unknow element name : " + localName);
		}
	}

	/**
	 * Initialise l'esemble des mappings chargé
	 * 
	 * @throws SAXException
	 *             SAX Exception renvoyée si impossible de créer un XMLReader
	 */
	@PostConstruct
	private void init() {
		LOGGER.info("Start Initialisation ModelParser");
		ClassPathResource modelMappingSchema = new ClassPathResource(CLASSPATH_CONFIG_MODEL_MAPPING_XSD);
		ClassPathResource modelMappingFile = new ClassPathResource(CLASSPATH_CONFIG_MODEL_MAPPING_XML);
		if (!modelMappingFile.exists() || !modelMappingSchema.exists()) {
			throw new RuntimeException("ModelMappingFile not found at path : " + modelMappingFile.getPath()
					+ " Or ModelMappingSchema not found at path : " + modelMappingSchema.getPath());
		}
		mappers = new HashMap<>();
		formatterStack = new ArrayList<>();
		readingMapper = null;
		editingFormatter = null;
		LOGGER.info("Start parsing file");
		try {
			XMLReader saxReader = XMLReaderFactory.createXMLReader();
			saxReader.setContentHandler(this);
			saxReader.parse(modelMappingFile.getURI().getPath());
		} catch (SAXException | IOException genericException) {
			throw new RuntimeException("Impossible to instantiate an XMLReader or imposible to parse input file",
					genericException);
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
		TYPE_ARRAY("array"), 
		NAME("name");

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
		 * 
		 * @param key
		 *            Valeur à rechercher
		 * @return Enumération correspondante
		 * @throws IllegalArgumentException
		 *             Est remontée si aucune énumération ne correspond
		 */
		public static ModelMappingXMLKeyEnum evaluate(String key) {
			for (ModelMappingXMLKeyEnum temp : values()) {
				if (temp.key.equals(key)) {
					return temp;
				}
			}
			throw new IllegalArgumentException("No enum found for value : " + key);
		}
	}
}
