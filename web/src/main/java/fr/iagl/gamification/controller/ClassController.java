package fr.iagl.gamification.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.MappingConstant;
import fr.iagl.gamification.exceptions.ClassExistsException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.services.ClassService;
import fr.iagl.gamification.validator.ClassForm;

/**
 * Tous les actions concernant la classe
 * 
 * @author Hélène Meyer
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ClassController {
	
	public static Logger log = Logger.getLogger(ClassController.class);
	
	/**
	 * Service pour la classe
	 */
	@Autowired
	private ClassService classService;
	
	/**
	 * Mapper
	 */
	@Autowired
	private Mapper mapper;
	
	/**
	 * Traite le formulaire de création d'une classe
	 * 
	 * @param classForm l'objet reçu par le formulaire
	 * @param bindingResult pour valider le formulaire
	 * @return l'objet crée et statut OK s'il a été ajoute sinon le message d'erreur et le statut BAD_REQUEST
	 */
	@RequestMapping(value = MappingConstant.POST_FORM_CLASS, method = RequestMethod.POST)
	public ResponseEntity submitClassForm(@Valid @RequestBody ClassForm classForm, BindingResult bindingResult) {
		List<String> errors;
		
		if (bindingResult.hasErrors()) {
			errors = bindingResult.getAllErrors().stream()
					.map(ObjectError::getDefaultMessage)
					.collect(Collectors.toList());
		} else {
			
			try {
				ClassModel createdClass = classService.createClass(mapper.map(classForm, ClassModel.class));
				return new ResponseEntity(createdClass, HttpStatus.OK);
			} catch (ClassExistsException e) {
				log.info("Class already existed");
				errors = Arrays.asList("CREATED");
			}
			
		}
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
}
