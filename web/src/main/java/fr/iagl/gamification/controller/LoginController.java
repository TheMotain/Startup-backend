package fr.iagl.gamification.controller;

import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.constants.CodeError;
import fr.iagl.gamification.constants.MappingConstant;
import fr.iagl.gamification.model.TeacherModel;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Contrôleur d'authentifiaction
 *
 * @author Hélène MEYER
 */
@RestController
@EnableWebSecurity
@PropertySource("classpath:config/url.properties")
public class LoginController extends AbstractController{
	
	/**
	 * Récupération du professeur seulement s'il est connecté et qu'il a le role TEACHER
	 * 
	 * @return le professeur
	 */
	@PreAuthorize("hasRole('TEACHER')")
	@ApiResponses(value = {@ApiResponse(code = HttpsURLConnection.HTTP_OK, response = TeacherModel.class, message = "Le professeur connecté"),
	@ApiResponse(code = HttpsURLConnection.HTTP_BAD_REQUEST, response = String.class, responseContainer = "list", message = "Liste des erreurs au niveau du professeur")})
	@RequestMapping(value=MappingConstant.CONNECT_TEACHER_PATH, method = RequestMethod.POST)
	public ResponseEntity<TeacherModel> loginTeacher() {
	    TeacherModel teacher = getTeacher();
	    if (teacher != null) {
	    	return new ResponseEntity(teacher, HttpStatus.OK);
	    }
	    return new ResponseEntity(Arrays.asList(CodeError.ERROR_TEACHER_NOT_FOUND), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Deconnexion du professeur
	 * 
	 * @param request la requete
	 * @param response la réponse
	 */
	@RequestMapping(value=MappingConstant.LOGOUT_PATH, method = RequestMethod.GET)
	public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	}
}
