package fr.iagl.gamification.services;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Interface pour la méthode de traitement à executer lors de la reception d'un message akka
 * Cela dépend du type de message reçu (insertion, modification ou suppression) et de la table traitée
 * 
 * @author Hélène Meyer
 *
 */
public interface RunnableHashMapService {
	
	/**
	 * Traitement suite à la reception d'un message akka en fonction de son type et de sa table
	 * 
	 * @param json les données de la notification
	 * @param simpMessageSendingOperations l'objet qui sert à faire un broadcast
	 * @throws JSONException 
	 */
	public void runMethod(JSONObject json) throws JSONException;
}
