package fr.iagl.gamification.services;

import org.json.JSONException;

import fr.iagl.gamification.model.Task;


/**
 * Interface du service pour les taches envoyées par Akka
 * 
 * @author Hélène Meyer
 */
public interface TaskService {

	/**
	 * Traitement d'une tâche :
	 * Récupération des éléments à traiter et traitement
	 * 
	 * @param task objet à traiter
	 * @throws JSONException
	 */
	public void treatTask(Task task) throws JSONException;
}
