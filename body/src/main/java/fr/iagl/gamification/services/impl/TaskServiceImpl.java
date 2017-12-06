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
public class TaskServiceImpl implements TaskService {

	@Autowired
	@Qualifier("runnablePointServiceImpl")
	private RunnableHashMapService runnablePointServiceImpl;
	
	@Autowired
	@Qualifier("runnableQcmServiceImpl")
	private RunnableHashMapService runnableQcmServiceImpl;
	
	@Autowired
	@Qualifier("runnableResultQcmServiceImpl")
	private RunnableHashMapService runnableResultQcmServiceImpl;

	/**
	 * Map qui contient toutes les méthodes à executer pour une table donnée et une
	 * action donnée
	 */
	private Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> runnableTableMap;

	/**
	 * Initialise la map des messages possibles
	 */
	@PostConstruct
	public void init() {
		runnableTableMap = new EnumMap<>(TableDatabase.class);

		// actions de la table message
		Map<ActionDatabase, RunnableHashMapService> mapActionsPointTable = new EnumMap<>(ActionDatabase.class);
		mapActionsPointTable.put(ActionDatabase.INSERT, runnablePointServiceImpl);
		mapActionsPointTable.put(ActionDatabase.UPDATE, runnablePointServiceImpl);
		
		// actions de la table QCM
		Map<ActionDatabase, RunnableHashMapService> mapActionsQCMTable = new EnumMap<>(ActionDatabase.class);
		mapActionsQCMTable.put(ActionDatabase.INSERT, runnableQcmServiceImpl);
		mapActionsQCMTable.put(ActionDatabase.UPDATE, runnableQcmServiceImpl);
		
		//action de la table result_qcm
		Map<ActionDatabase, RunnableHashMapService> mapActionResultQCMTable = new EnumMap<>(ActionDatabase.class);
		mapActionResultQCMTable.put(ActionDatabase.INSERT, runnableResultQcmServiceImpl);
		
		// insertion de toutes les actions des tables
		runnableTableMap.put(TableDatabase.POINT, mapActionsPointTable);
		runnableTableMap.put(TableDatabase.QCM, mapActionsQCMTable);
		runnableTableMap.put(TableDatabase.RESULT_QCM, mapActionResultQCMTable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.iagl.gamification.services.TaskService#treatTask(fr.iagl.gamification.
	 * model.TaskModel)
	 */
	@Override
	public void treatTask(TaskModel task) throws JSONException {
		JSONObject json = task.getNotification();

		String table = json.getString("table").toUpperCase();
		String action = json.getString("type");
		JSONObject data = (JSONObject) json.get("data");

		TableDatabase[] tables = TableDatabase.values();
		ActionDatabase[] actions = ActionDatabase.values();
		if (!Arrays.stream(tables).anyMatch(t -> t.toString().equals(table))
				|| !Arrays.stream(actions).anyMatch(a -> a.toString().equals(action))) {
			return;
		}
		if (runnableTableMap.containsKey(TableDatabase.valueOf(table))
				&& runnableTableMap.get(TableDatabase.valueOf(table)).containsKey(ActionDatabase.valueOf(action))) {
			runnableTableMap.get(TableDatabase.valueOf(table)).get(ActionDatabase.valueOf(action)).runMethod(data);
		}
	}

	/**
	 * Setter de l'attribut {@link TaskServiceImpl#runnableTableMap}
	 * 
	 * @param map
	 *            Map des messages à utiliser
	 */
	public void setMap(Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> map) {
		this.runnableTableMap = map;
	}

	/**
	 * Getter de l'attribut {@link TaskServiceImpl#runnableTableMap}
	 * 
	 * @return Map des messages à utiliser
	 */
	public Map<TableDatabase, Map<ActionDatabase, RunnableHashMapService>> getMap() {
		return this.runnableTableMap;
	}

}
