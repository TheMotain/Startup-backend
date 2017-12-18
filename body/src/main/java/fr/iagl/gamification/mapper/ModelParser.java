package fr.iagl.gamification.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import fr.iagl.gamification.mapper.composite.MappingJSONArray;
import fr.iagl.gamification.mapper.composite.MappingJSONAttribute;
import fr.iagl.gamification.mapper.composite.MappingJSONAttribute.JSONTypeEnum;
import fr.iagl.gamification.mapper.composite.MappingJSONObject;
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
	 * Chemin vers le fichier ModelMappingFile
	 */
	private static final String CLASSPATH_CONFIG_MODEL_MAPPING_XML = "config/model-mapping.xml";
	
	/**
	 * Chaine de caractères pour le séparateur de chemin d'objet java
	 */
	private static final String OBJECT_PATH_SEPARATOR = ".";

	/**
	 * Liste des mappers à réaliser
	 */
	private static Map<MappingEnum, MappingJSONFormatter> mappers;

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

	/**
	 * Permet de savoir si un mapping java est en lecture.<br>
	 * Cela permet de récuperer la valeur entre les balises.
	 */
	private boolean readingMappingObject;
	
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
				throw new SAXException("XML file is invalid try to create a json attribute out of mapper");
			}
			if(editingFormatter instanceof MappingJSONAttribute) {
				throw new SAXException("XML file is invalid try to create a json attribute inside one other");
			}
			stackFormatter(attributes, new MappingJSONAttribute(
					new Tuple<>(JSONTypeEnum.valueOf(attributes.getValue(ModelMappingXMLKeyEnum.JSON_TYPE.key)),
							StringUtils.isNotBlank(attributes.getValue(ModelMappingXMLKeyEnum.OBJECT_TYPE.key))
									? JSONTypeEnum.valueOf(attributes.getValue(ModelMappingXMLKeyEnum.OBJECT_TYPE.key))
									: null)));
			break;
		case JSON_OBJECT:
			if(editingFormatter == null){
				throw new SAXException("XML file is invalid try to create a json object out of mapper");
			}
			stackFormatter(attributes, new MappingJSONObject());
		case JSON_ARRAY:
			if(editingFormatter == null) {
				throw new SAXException("XML file is invalid try to create a json array out of mapper");
			}
			stackFormatter(attributes, new MappingJSONArray<>());
			break;
		case MAPPING_OBJECT:
			if(editingFormatter == null){
				throw new SAXException("XML file is invalid try to create a json array out of mapper");
			}
			if(!(editingFormatter instanceof MappingJSONAttribute)) {
				throw new SAXException("XML file is invalid try to create a object mapper out of a json attribute");
			}
			if(readingMappingObject) {
				throw new SAXException("XML file is invalid try to create a object mapper inside an other");
			}
			readingMappingObject = true;
			break;
		default:
			throw new SAXException("Unknow element name : " + localName);
		}
	}

	/**
	 * Permet de creer et d'empiler dans la stack un niveau d'arborescence un nouveau JSONFormatter
	 * @param attributes liste des attribut de la balise en cours de lecture
	 * @param formatter Formatter à creer
	 * @throws SAXException Est remonté si tentative de créer un objet avec une clé dupliquée
	 */
	private void stackFormatter(Attributes attributes, MappingJSONFormatter formatter) throws SAXException {
		if(StringUtils.isEmpty(attributes.getValue(ModelMappingXMLKeyEnum.NAME.key))){
			throw new SAXException("XML file is invalid json key for attribute / object / array is empty");
		}
		if(!editingFormatter.createFormatter(attributes.getValue(ModelMappingXMLKeyEnum.NAME.key), formatter)) {
			throw new SAXException("XML file is invalid try to create a json object with duplicated keys");
		}
		formatterStack.add(0, editingFormatter);
		editingFormatter = formatter;
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
			if(!(editingFormatter instanceof MappingJSONObject)){
				throw new SAXException("XML file is invalid try to close a none mapper element");
			}
			readingMapper = null;
			editingFormatter = null;
			break;
		case JSON_ATTRIBUTE:
			if(formatterStack.isEmpty()) {
				throw new SAXException("XML file is invalid try to close an attribute out of mapper");
			}
			if(!(editingFormatter instanceof MappingJSONAttribute)) {
				throw new SAXException("XML file is invalid try to close a none json attribute");
			}
			editingFormatter = formatterStack.remove(0);
			break;
		case JSON_OBJECT:
			if(formatterStack.isEmpty()) {
				throw new SAXException("XML file is invalid try to close an object out of mapper");
			}
			if(!(editingFormatter instanceof MappingJSONObject)) {
				throw new SAXException("XML file is invalid try to close a none json object");
			}
			editingFormatter = formatterStack.remove(0);
			break;
		case JSON_ARRAY :
			if(formatterStack.isEmpty()) {
				throw new SAXException("XML file is invalid try to close an array out of mapper");
			}
			if(!(editingFormatter instanceof MappingJSONArray)) {
				throw new SAXException("XML file is invalid try to close a none json array");
			}
			editingFormatter = formatterStack.remove(0);
			break;
		case MAPPING_OBJECT:
			if(!readingMappingObject) {
				throw new SAXException("XML file is invalid try to clone a none mapping object element");
			}
			readingMappingObject = false;
			break;
		default:
			throw new SAXException("Unknow element name : " + localName);
		}
	}

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(readingMappingObject) {
			String value = new String(ch,start,length);
			if(StringUtils.isEmpty(value)) {
				throw new SAXException("XML file is invalid need to fill the value of one mapping object element");
			}
			((MappingJSONAttribute)editingFormatter).setObjectPath(new String(ch, start, length).split(OBJECT_PATH_SEPARATOR));
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
		ClassPathResource modelMappingFile = new ClassPathResource(CLASSPATH_CONFIG_MODEL_MAPPING_XML);
		if (!modelMappingFile.exists()) {
			throw new RuntimeException("ModelMappingFile not found at path : " + modelMappingFile.getPath());
		}
		mappers = new HashMap<>();
		formatterStack = new ArrayList<>();
		readingMapper = null;
		editingFormatter = null;
		readingMappingObject = false;
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
	public static MappingJSONFormatter getMapper(MappingEnum mapping) {
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
		OBJECT_TYPE("objectType"), 
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
