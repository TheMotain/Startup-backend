package fr.iagl.gamification.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Classe abstraite définissant les informations similaires à tous les controllers
 * @author dalencourt
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@PropertySource("classpath:config/url.properties")
public interface AbstractController {

}
