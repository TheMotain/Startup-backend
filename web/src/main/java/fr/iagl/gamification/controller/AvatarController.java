package fr.iagl.gamification.controller;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.model.PriceModel;
import fr.iagl.gamification.services.AvatarService;
import fr.iagl.gamification.services.InventaireService;
import fr.iagl.gamification.services.PriceService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Contrôler pour la manipulation des avatar
 * @author dalencourt
 *
 */
@RestController()
public class AvatarController extends AbstractController{

	/**
	 * Service principal de manipulation des avatars
	 */
	@Autowired
	private AvatarService avatarService;
		
	/**
	 * Service qui liste tous les avatar achetables avec le prix
	 */
	@Autowired
	private PriceService priceService;
	
	/**
	 * Service qui permet de gérer l'inventaire d'un élève
	 */
	@Autowired
	private InventaireService inventaireService;
	
	/**
	 * Récupère l'avatar d'un élève
	 * @param id Identifiant de l'élève voulant récupérer son avatar
	 * @return Réponse contenant le résultat
	 */
	@RequestMapping(value = MappingConstant.AVATAR_CRUD, method = RequestMethod.GET)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = AvatarModel.class, message = "Image avatar pour l'utilisateur"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer="list", message = "L'utilisateur n'existe pas")})
	public ResponseEntity getAvatar(@PathVariable("studentID") Long id) {
		try {
			return new ResponseEntity<>(avatarService.findAvatar(id),HttpStatus.OK);
		}catch(GamificationServiceException gse) {
			return new ResponseEntity<>(gse.getErrors(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Modifie l'avatar actuellement sélectionné
	 * @param id Identifiant de compte élève
	 * @param avatar avatar à selectionner
	 * @return Réponse contenant le résultat
	 */
	@RequestMapping(value = MappingConstant.AVATAR_CRUD, method = RequestMethod.PUT)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = AvatarModel.class, message = "Nouvel avatar pour l'utilisateur"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer="list", message = "L'utilisateur n'existe pas")})
	public ResponseEntity updateAvatar(@PathVariable("studentID") Long id, @RequestParam("avatar") String avatar) {
		try {
			if(StringUtils.isBlank(avatar)) {
				throw new GamificationServiceException(Arrays.asList("Format de l'avatar invalide"));
			}
			return new ResponseEntity<>(avatarService.updateAvatar(id,avatar),HttpStatus.OK);
		}catch(GamificationServiceException gse) {
			return new ResponseEntity<>(gse.getErrors(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Récupère la liste de tous les avatars achetable avec le prix pour chaque
	 * @return Réponse contenant le résultat
	 */
	@RequestMapping(value = MappingConstant.AVATAR_PRICE, method = RequestMethod.GET)
	@ApiResponse(code = HttpURLConnection.HTTP_OK, response=PriceModel.class, responseContainer="list", message="Liste des avatars avec leur prix")
	public ResponseEntity<List<PriceModel>> priceList(){
		return new ResponseEntity<>(priceService.listAllAvatar(), HttpStatus.OK);
	}
	
	/**
	 * Récupère la liste de tous les avatars acheté pour l'élève en paramètres
	 * @param id identifiant de l'élève
	 * @return Réponse contenant le résultat ou une erreur
	 */
	@RequestMapping(value = MappingConstant.AVATAR_BOUGTH, method = RequestMethod.GET)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = String.class, responseContainer="list", message = "Liste des avatars acheté pour l'utilisateur"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "L'utilisateur n'existe pas")})
	public ResponseEntity getBougthAvatar(@PathVariable("studentID") Long id) {
		try {
			return new ResponseEntity<>(inventaireService.getAllBougthAvatar(id), HttpStatus.OK);
		}catch(GamificationServiceException gse) {
			return new ResponseEntity<>(gse.getErrors(), HttpStatus.BAD_REQUEST);
			
		}
	}
}
