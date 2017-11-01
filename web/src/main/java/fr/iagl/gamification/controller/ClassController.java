package fr.iagl.gamification.controller;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	 * @return "ok" si la classe a été ajoutée et sinon le message d'erreur
	 */
	@RequestMapping(value = MappingConstant.POST_FORM_CLASS, method = RequestMethod.POST)
	public String submitClassForm(@Valid @ModelAttribute("classForm") ClassForm classForm, BindingResult bindingResult) {
		// vérifie le contenu du formulaire
		if (bindingResult.hasErrors()) {
			// retourne le premier message d'erreur
			return bindingResult.getAllErrors().get(0).getDefaultMessage();
		}
		// transforme l'objet formulaire en un autre objet du module data
		ClassModel classe = mapper.map(classForm, ClassModel.class);
		classService.createClass(classe);
		return "ok";
	}
	
}
