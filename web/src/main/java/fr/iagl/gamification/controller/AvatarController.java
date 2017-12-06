package fr.iagl.gamification.controller;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.exceptions.GamificationServiceException;
import fr.iagl.gamification.model.AvatarModel;
import fr.iagl.gamification.services.AvatarService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
public class AvatarController implements AbstractController{

	@Autowired
	private AvatarService avatarService;
		
	@RequestMapping(value = MappingConstant.AVATAR_CRUD, method = RequestMethod.GET)
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = AvatarModel.class, message = "Image avatar pour l'utilisateur"),
			@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer="list", message = "L'utilisateur n'existe pas")})
	public ResponseEntity getAvatar(@PathVariable("studentID") Long id) throws GamificationServiceException {
		try {
			return new ResponseEntity<>(avatarService.findAvatar(id),HttpStatus.OK);
		}catch(GamificationServiceException gse) {
			return new ResponseEntity<>(gse.getErrors(), HttpStatus.BAD_REQUEST);
		}
	}
}
