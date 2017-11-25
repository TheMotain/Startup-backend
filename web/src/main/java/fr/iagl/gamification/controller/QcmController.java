package fr.iagl.gamification.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.QcmModel;
import fr.iagl.gamification.object.QcmObject;
import fr.iagl.gamification.services.QcmService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.QcmForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class QcmController {

	/**
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(QcmController.class);
	
	/**
	 * Service pour la gestion des points
	 */
	@Autowired
	private QcmService qcmService;
	
	/**
	 * Mapper
	 */
	@Autowired
	private Mapper mapper;
	
	/**
	 * Récupère tous les qcm
	 * 
	 * @return tous les qcm
	 */
	@RequestMapping(value = MappingConstant.QCM_PATH_ROOT, method = RequestMethod.GET)
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = QcmObject.class, responseContainer = "list", message = "Liste des qcm")
	public ResponseEntity<List<QcmObject>> getAllQcm() {
		LOG.info("Récupération de la liste des QCM");
		List<QcmModel> result = qcmService.getAllQcm();
		List<QcmObject> qcms = new ArrayList<>();
		Optional.ofNullable(result)
					.orElseGet(Collections::emptyList)
					.iterator()
					.forEachRemaining(e -> qcms.add(mapper.map(e, QcmObject.class)));
		return new ResponseEntity<>(qcms, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = MappingConstant.POINTS_PATH_ROOT, method = RequestMethod.POST)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = QcmObject.class, message = "Les qcm modifiés/ajoutés"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs au niveau du formulaire / La classe n'existe pas")})
	public ResponseEntity<QcmObject> submitQcmForm(@Valid @RequestBody QcmForm qcmForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {
			
			try {
				QcmModel updatedQcm = qcmService.saveQcm(mapper.map(qcmForm, QcmModel.class));
				if (updatedQcm != null) {
					return new ResponseEntity<>(mapper.map(updatedQcm, QcmObject.class), HttpStatus.OK);
				}
			} catch (GamificationServiceException e) {
				LOG.info("Erreur lors de l'appel au service qcmService.saveQcm");
				errors = e.getErrors();
			}
			
		}
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
}
