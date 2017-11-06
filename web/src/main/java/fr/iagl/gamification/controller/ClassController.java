package fr.iagl.gamification.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.iagl.gamification.MappingConstant;
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
	 * Visualisation de la vue du formulaire d'ajout d'une classe
	 * TODO supprimer à la fin
	 * 
	 * @param classForm l'objet du formulaire
	 * @return la vue
	 */
	@GetMapping(MappingConstant.POST_FORM_CLASS)
    public ModelAndView showForm(ClassForm classForm) {
        return new ModelAndView("form");
    }
	
	/**
	 * Traite le formulaire de création d'une classe
	 * 
	 * @param classForm l'objet reçu par le formulaire
	 * @param bindingResult pour valider le formulaire
	 * @return l'objet crée et statut OK s'il a été ajoute sinon le message d'erreur et le statut BAD_REQUEST
	 */
	@RequestMapping(value = MappingConstant.POST_FORM_CLASS, method = RequestMethod.POST)
	public ResponseEntity<ClassModel> submitClassForm(@Valid @RequestBody ClassForm classForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		
		ClassModel createdClass;
		try {
			createdClass = classService.createClass(mapper.map(classForm, ClassModel.class));
			return new ResponseEntity(createdClass, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Logger
			return new ResponseEntity(Arrays.asList("CREATED"), HttpStatus.BAD_REQUEST);
		}
	}
	
}
