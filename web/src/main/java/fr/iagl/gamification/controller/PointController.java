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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.PointModel;
import fr.iagl.gamification.services.PointService;
import fr.iagl.gamification.utils.RequestTools;
import fr.iagl.gamification.validator.PointForm;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Gestion des points de l'élève
 * 
 * @author Hélène MEYER
 *
 */
@RestController
public class PointController extends AbstractController{

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(PointController.class);
	
	/**
	 * Service pour la gestion des points
	 */
	@Autowired
	private PointService pointService;
	
	/**
	 * Mapper
	 */
	@Autowired
	private Mapper mapper;
	
	@RequestMapping(value = MappingConstant.POINTS_PATH_ROOT_WITH_USERID, method = RequestMethod.GET)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = PointForm.class, message = "Détail des points pour l'utilisateur"),
		@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer="list", message = "L'utilisateur n'existe pas")})
	public ResponseEntity<PointForm> getPoint(@PathVariable("userID") Long userID){
		try {
			PointModel model = pointService.getPoint(userID);
			return new ResponseEntity<>(mapper.map(model, PointForm.class), HttpStatus.OK);
		}catch(GamificationServiceException gse) {
			LOGGER.error("Etudiant non reconnu pour la récupération des points avec l'id : " + userID);
			return new ResponseEntity(gse.getErrors(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Récupère tous les points
	 * 
	 * @return tous les points
	 */
	@RequestMapping(value = MappingConstant.POINTS_PATH_ROOT, method = RequestMethod.GET)
	@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = PointForm.class, responseContainer = "list", message = "Liste des points des élèves")
	public ResponseEntity<List<PointForm>> getAllPoints() {
		LOGGER.info("Récupération de la liste des points");
		List<PointModel> result = pointService.getPoints();
		List<PointForm> points = new ArrayList<>();
		Optional.ofNullable(result)
					.orElseGet(Collections::emptyList)
					.iterator()
					.forEachRemaining(e -> points.add(mapper.map(e, PointForm.class)));
		return new ResponseEntity<>(points, HttpStatus.OK);
	}
	
	/**
	 * Crée ou modifie le nombre de points 
	 * 
	 * @param pointForm le formulaire pour ajouter des bonus/malus
	 * @param bindingResult validateur
	 * @return les points de l'élève ou la liste des erreurs
	 */
	@RequestMapping(value = MappingConstant.POINTS_PATH_ROOT, method = RequestMethod.POST)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = PointForm.class, message = "Les points modifiés"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs au niveau du formulaire / L'élève n'existe pas")})
	public ResponseEntity<PointForm> submitPointForm(@Valid @RequestBody PointForm pointForm, BindingResult bindingResult) {
		List<String> errors = Arrays.asList(CodeError.SAVE_FAIL);
		
		if (bindingResult.hasErrors()) {
			errors = RequestTools.transformBindingErrors(bindingResult);
		} else {
			
			try {
				PointModel updatedPoint = pointService.updatePoint(mapper.map(pointForm, PointModel.class), pointForm.getIdStudent());
				if (updatedPoint != null) {
					return new ResponseEntity<>(mapper.map(updatedPoint, PointForm.class), HttpStatus.OK);
				}
			} catch (GamificationServiceException e) {
				LOGGER.info("Erreur lors de l'appel au service pointService.updatePoint");
				errors = e.getErrors();
			}
			
		}
		return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
}
