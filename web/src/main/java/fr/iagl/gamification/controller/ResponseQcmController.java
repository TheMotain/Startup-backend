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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.ResultQcmModel;
import fr.iagl.gamification.services.ResultQcmService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.ResultQcmForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Contrôleur qui gère les requêtes HTTP des réponses des QCM
 *
 * @author Hélène MEYER
 *
 */
@RestController
public class ResponseQcmController {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(QcmController.class);
	
	/**
	 * service des résultats du QCM
	 */
	@Autowired
	private ResultQcmService resultQcmService;
	
	/**
	 * Mapper
	 */
	@Autowired
	private Mapper mapper;
	
	/**
	 * Récupération de la liste des réponses du QCM avec son id
	 * 
	 * @param qcmID l'identifiant du QCM
	 * @return la liste des réponses
	 */
	@RequestMapping(value = MappingConstant.QCM_RESULT_PATH_ROOT, method = RequestMethod.GET)
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = ResultQcmModel.class, responseContainer = "list", message = "Liste des réponses des qcm")
	public ResponseEntity<List<ResultQcmModel>> getAllResultQcm(@PathVariable("qcmID") Long qcmID) {
		LOG.info("Récupération de la liste des Réponses du QCM [" + qcmID + "]");
		List<ResultQcmModel> result = resultQcmService.getAllQcmResultsByIdQcm(qcmID);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	/**
	 * Envoyer les résultats du QCM
	 * 
	 * @param resultQcmForm  formulaire d'envoie
	 * @param bindingResult pour valider les données
	 * @return les résultats du QCM enregistrés
	 */
	@RequestMapping(value = MappingConstant.QCM_SEND_RESULT_ROOT, method = RequestMethod.POST)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = ResultQcmModel.class, message = "Les résultats envoyé par l'élève"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs au niveau du formulaire / Les réponses n'existent pas")})
	public ResponseEntity<List<ResultQcmModel>> submitQcmForm(@Valid @RequestBody ResultQcmForm resultQcmForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {
			
			try {
				List<ResultQcmModel> updatedResultQcm = resultQcmService.saveResultQcm(resultQcmForm.getIdAnswer(), resultQcmForm.getIdStudent());
				if (updatedResultQcm != null) {
					return new ResponseEntity<>(updatedResultQcm, HttpStatus.OK);
				}
			} catch (GamificationServiceException e) {
				LOG.info("Erreur lors de l'appel au service resultQcmService.saveResultQcm");
				errors = e.getErrors();
			}
			
		}
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}

	
}
