package fr.iagl.gamification.controller;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.exceptions.ClassExistsException;
import fr.iagl.gamification.model.ClassModel;
import fr.iagl.gamification.services.ClassService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.ClassForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Tous les actions concernant la classe
 * 
 * @author Hélène Meyer
 *
 */
@RestController
public class ClassController extends AbstractController {
	
	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(ClassController.class);
	
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
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = ClassModel.class, message = "La classe créée"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs au niveau du formulaire / La classe existe déjà")})
	public ResponseEntity submitClassForm(@Valid @RequestBody ClassForm classForm, BindingResult bindingResult) {
		List<String> errors;
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {
			
			try {
				ClassModel createdClass = classService.createClass(mapper.map(classForm, ClassModel.class));
				return new ResponseEntity(createdClass, HttpStatus.OK);
			} catch (ClassExistsException e) {
				LOG.info("Class already existed");
				errors = Arrays.asList(CodeError.ERROR_EXISTS_CLASS);
			}
			
		}
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
}
