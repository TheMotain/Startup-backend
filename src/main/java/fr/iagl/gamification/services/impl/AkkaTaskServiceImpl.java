package fr.iagl.gamification.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import fr.iagl.gamification.listener.akka.Task;
import fr.iagl.gamification.services.AkkaTaskService;
import fr.iagl.gamification.services.RunnableHashMap;
import fr.iagl.gamification.utils.ActionDatabase;
import fr.iagl.gamification.utils.TableDatabase;

/**
 * Lance le traitement d'un message reçu 
 * 
 * @author Hélène Meyer
 *
 */
@Component("akkaTaskService")
public class AkkaTaskServiceImpl implements AkkaTaskService{

	
	/**
	 * Map qui contient toutes les méthodes à executer pour une table donnée et une action donnée
	 */
	private Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map;
	
	/**
	 * Constructeur qui initialise la map
	 */
	public AkkaTaskServiceImpl() {
		initMap();
	}
	
	/**
	 * initialise la map
	 */
	public void setMap(Map<TableDatabase, Map<ActionDatabase, RunnableHashMap>> map) {
		this.map = map;
	}

	/**
	 * Initialisation de la map (à compléter)
	 * 
	 * Pour chaque table de la base de données, on a une valeur dans la map, également pour chaque action donnée
	 */
	private void initMap() {
		this.map = new HashMap<>();
		
		//actions de la table message
		Map<ActionDatabase, RunnableHashMap> mapActionsMessageTable = new HashMap<>();
		mapActionsMessageTable.put(ActionDatabase.INSERT, new RunnableInsertMessageServiceImpl());
		
		//insertion de toutes les actions des tables
		this.map.put(TableDatabase.MESSAGE, mapActionsMessageTable);
	}
	
	
	@Override
	public void treatTask(Task task) throws JSONException {
		JSONObject json = task.getNotification();
		
		String table = json.getString("table").toUpperCase();
		String action = json.getString("type");
		JSONObject data = (JSONObject) json.get("data");
		
		TableDatabase[] tables = TableDatabase.values();
		ActionDatabase[] actions = ActionDatabase.values();
		if (! Arrays.stream(tables).anyMatch(t -> t.toString().equals(table)) || ! Arrays.stream(actions).anyMatch(a -> a.toString().equals(action))) {
			return;
		}
		if (this.map.containsKey(TableDatabase.valueOf(table)) && this.map.get(TableDatabase.valueOf(table)).containsKey(ActionDatabase.valueOf(action))) {
			this.map.get(TableDatabase.valueOf(table)).get(ActionDatabase.valueOf(action)).runMethod(data, task.getMessagingTemplate());
		}
	}
}
