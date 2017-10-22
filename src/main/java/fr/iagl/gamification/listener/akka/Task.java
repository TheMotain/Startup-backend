package fr.iagl.gamification.listener.akka;

import org.json.JSONObject;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import fr.iagl.gamification.services.impl.AkkaTaskServiceImpl;

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
    
    /**
     * service de traitement des taches akka
     */
    private AkkaTaskServiceImpl service;
    
    /**
     * broadcaster
     */
    private SimpMessageSendingOperations messagingTemplate;
    
    public Task(final JSONObject notification, final AkkaTaskServiceImpl service, final SimpMessageSendingOperations messagingTemplate) {
        this.notification = notification;
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    public JSONObject getNotification() {
        return notification;
    }
    
    public AkkaTaskServiceImpl getService() {
        return service;
    }
    
    public SimpMessageSendingOperations getMessagingTemplate() {
    	return messagingTemplate;
    }
}
