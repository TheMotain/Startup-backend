package fr.iagl.gamification.model;

import org.json.JSONObject;

/**
 * Message envoyé aux acteurs
 * 
 * @author Hélène Meyer
 *
 */
public class TaskModel {

    /**
     * Notification de la base de donne reçue
     */
    private final JSONObject notification;
    
    public TaskModel(final JSONObject notification) {
        this.notification = notification;
    }

    public JSONObject getNotification() {
        return notification;
    }
}
