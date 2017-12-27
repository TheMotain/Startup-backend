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
	
	/**
	 * service des professeurs
	 */
	@Autowired
	protected TeacherService teacherService;

	/**
	 * Récupération du nom de l'utilisateur connecté
	 * 
	 * @return le nom de l'utilisateur
	 */
	protected String getPrincipal(){
		String userName = null;
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return null;
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * Récupération du professeur connecté
	 * 
	 * @return le professeur
	 */
	protected TeacherModel getTeacher(){
		return teacherService.getTeacherByMail(getPrincipal());
	}
}
