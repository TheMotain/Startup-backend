package fr.iagl.gamification.services.impl;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.model.TaskModel;
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

	/**
	 * Initialise la map des messages possibles
	 */
	@PostConstruct
	public void init() {
		map = new EnumMap(TableDatabase.class);
		
		//actions de la table message
		Map<ActionDatabase, RunnableHashMapService> mapActionsMessageTable = new EnumMap(ActionDatabase.class);
		mapActionsMessageTable.put(ActionDatabase.INSERT, runnableInsertMessageServiceImpl);
		
		//insertion de toutes les actions des tables
		map.put(TableDatabase.MESSAGE, mapActionsMessageTable);
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.iagl.gamification.services.TaskService#treatTask(fr.iagl.gamification.model.TaskModel)
	 */
	@Override
	public void treatTask(TaskModel task) throws JSONException {
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
	
	/**
	 * Setter de l'attribut {@link TaskServiceImpl#map}
	 * @param map Map des messages à utiliser
	 */
	public void setMap(Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map) {
		this.map = map;
	}

	/**
	 * Getter de l'attribut {@link TaskServiceImpl#map}
	 * @return Map des messages à utiliser
	 */
	public Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> getMap() {
		return this.map;
	}

}
