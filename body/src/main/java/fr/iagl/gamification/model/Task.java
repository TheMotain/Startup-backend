package fr.iagl.gamification.model;

import org.json.JSONObject;

/**
 * Message envoyé aux acteurs
 * 
 * @author Hélène Meyer
 *
 */
public class Task {

    /**
     * Notification de la base de donne reçue
     */
    private final JSONObject notification;
    
    public Task(final JSONObject notification) {
        this.notification = notification;
    }

    public JSONObject getNotification() {
        return notification;
    }
}
