package fr.iagl.gamification.controller;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.TeacherModel;
import fr.iagl.gamification.services.TeacherService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.TeacherForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Contrôleur de la gestion du professeur
 *
 * @author Hélène MEYER
 *
 */
@RestController
@PropertySource("classpath:config/url.properties")
public class TeacherController implements AbstractController {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(TeacherController.class);
	
	/**
	 * Service pour le professeur
	 */
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * Mapper
	 */
	@Autowired
	private Mapper mapper;
	
	/**
	 * Récupère tous les professeurs
	 * 
	 * @return tous les professeurs
	 */
	@RequestMapping(value = MappingConstant.TEACHER_PATH_ROOT, method = RequestMethod.GET)
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = TeacherModel.class, responseContainer = "list", message = "Liste des professeurs")
	public ResponseEntity<List<TeacherModel>> getAllTeacher() {
		LOG.info("Récupération de la liste des professeurs");
		List<TeacherModel> result = teacherService.getAllTeacher();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * Enregistre un nouveau professeur
	 * 
	 * @param teacherForm le formulaire du professeur
	 * @param bindingResult pour vérifier les éléments entrants
	 * @return le nouveau professeur créé
	 */
	@RequestMapping(value = MappingConstant.TEACHER_PATH_ROOT, method = RequestMethod.POST)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = TeacherModel.class, message = "Le professeur créé"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs au niveau du formulaire / Le mail existe déjà")})
	public ResponseEntity<TeacherModel> submitTeacherForm(@Valid @RequestBody TeacherForm teacherForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {
			
			try {
				TeacherModel createdTeacher = teacherService.createTeacher(mapper.map(teacherForm, TeacherModel.class));
				if (createdTeacher != null) {
					return new ResponseEntity<>(createdTeacher, HttpStatus.OK);
				}
			} catch (GamificationServiceException e) {
				LOG.info("Erreur lors de l'appel au service teacherService.createTeacher");
				errors = e.getErrors();
			}
			
		}
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
}
