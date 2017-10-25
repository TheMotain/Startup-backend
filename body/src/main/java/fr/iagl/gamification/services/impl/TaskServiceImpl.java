package fr.iagl.gamification.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.listener.akka.Task;
import fr.iagl.gamification.services.RunnableHashMapService;
import fr.iagl.gamification.services.TaskService;
import fr.iagl.gamification.utils.ActionDatabase;
import fr.iagl.gamification.utils.TableDatabase;

/**
 * Lance le traitement d'un message reçu 
 * 
 * @author Hélène Meyer
 *
 */
@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	@Qualifier("runnableInsertMessageServiceImpl")
	private RunnableHashMapService runnableInsertMessageServiceImpl;
	
	/**
	 * Map qui contient toutes les méthodes à executer pour une table donnée et une action donnée
	 */
	private Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map;

	@PostConstruct
	public void init() {
		map = new HashMap<>();
		
		//actions de la table message
		Map<ActionDatabase, RunnableHashMapService> mapActionsMessageTable = new HashMap<>();
		mapActionsMessageTable.put(ActionDatabase.INSERT, runnableInsertMessageServiceImpl);
		
		//insertion de toutes les actions des tables
		map.put(TableDatabase.MESSAGE, mapActionsMessageTable);
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
		if (map.containsKey(TableDatabase.valueOf(table)) && map.get(TableDatabase.valueOf(table)).containsKey(ActionDatabase.valueOf(action))) {
			map.get(TableDatabase.valueOf(table)).get(ActionDatabase.valueOf(action)).runMethod(data);
		}
	}
}
