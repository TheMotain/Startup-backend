package fr.iagl.gamification.listener.akka;

import org.json.JSONObject;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import fr.iagl.gamification.services.impl.TaskServiceImpl;

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
