package fr.iagl.gamification.services;

import java.util.Map;

import org.json.JSONException;

import fr.iagl.gamification.model.Task;
import fr.iagl.gamification.utils.ActionDatabase;
import fr.iagl.gamification.utils.TableDatabase;


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

	/**
	 * Setter de la map
	 * 
	 * @param map nouvelle map
	 */
	public void setMap(Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map);

	/**
	 * Getter de la map
	 */
	public Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> getMap();

	/**
	 * Init la map
	 */
	public void init();

}
