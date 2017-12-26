package fr.iagl.gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.iagl.gamification.model.TeacherModel;
import fr.iagl.gamification.services.TeacherService;

/**
 * Classe abstraite définissant les informations similaires à tous les controllers
 * @author dalencourt
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@PropertySource("classpath:config/url.properties")
public abstract class AbstractController {
	
	@Autowired
	protected TeacherService teacherService;

	protected String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	protected TeacherModel getTeacher(){
		return teacherService.getTeacherByMail(getPrincipal());
	}
}
