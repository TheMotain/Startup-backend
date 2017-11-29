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

    /**
     * Constructeur
     * @param notification à traiter
     */
    public TaskModel(final JSONObject notification) {
        this.notification = notification;
    }

    /**
     * Getter de la notification
     * @return la notification à traiter
     */
    public JSONObject getNotification() {
        return notification;
    }
}
