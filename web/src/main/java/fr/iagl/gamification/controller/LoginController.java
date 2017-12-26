package fr.iagl.gamification.controller;

import java.util.Arrays;

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

import fr.iagl.gamification.model.TeacherModel;

@RestController
@EnableWebSecurity
@PropertySource("classpath:config/url.properties")
public class LoginController extends AbstractController{
	
	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value="/loginTeacher", method = RequestMethod.POST)
	public ResponseEntity<TeacherModel> loginTeacher() {
	    TeacherModel teacher = getTeacher();
	    if (teacher != null) {
	    	return new ResponseEntity(teacher, HttpStatus.OK);
	    }
	    return new ResponseEntity(Arrays.asList(), HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public void logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	}
}
