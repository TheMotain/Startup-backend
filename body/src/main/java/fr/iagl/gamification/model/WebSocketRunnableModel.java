package fr.iagl.gamification.model;

import java.util.EnumMap;
import java.util.Map;

import fr.iagl.gamification.services.RunnableHashMapService;
import fr.iagl.gamification.utils.ActionDatabase;

/**
 * Model permettant de stoquer les informations de généricité pour l'envois des messages websocket
 * @author dalencourt
 *
 */
public class WebSocketRunnableModel {

	private Map<ActionDatabase, RunnableHashMapService> mapActionsTable = new EnumMap(ActionDatabase.class);
	
	private Class model;
	
	private String channel;
	
}
