package fr.iagl.gamification.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controleur abstrait. Tous les controleurs doivent hériter de ce dernier
 * @author dalencourt
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public abstract class AbstractController {

}
