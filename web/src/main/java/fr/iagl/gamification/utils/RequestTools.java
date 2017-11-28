package fr.iagl.gamification.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * Contient un ensemble de méthodes utiles à la gestion des requêtes
 * @author ALEX
 *
 */
public class RequestTools {
	
	/**
	 * Permet de transformer la liste des erreurs en une liste de messages
	 * @return
	 */
	public static List<String> transformBindingErrors(BindingResult binding){
		return binding.getAllErrors().stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
	}

	/**
	 * La classe n'est pas instanciable
	 */
	private RequestTools() {
		
	}
}
