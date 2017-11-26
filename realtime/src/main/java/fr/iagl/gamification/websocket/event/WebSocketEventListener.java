package fr.iagl.gamification.websocket.event;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import fr.iagl.gamification.entity.Singleton;

/**
 * Listener pour accepter et fermer les websockets
 * @author ALEX
 *
 */
@Component
public class WebSocketEventListener {

	/**
	 * Logger
	 */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    /**
     * Template d'envoi de messages
     */
    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    /**
     * Ecoute la connexion de nouvelles sockets
     * @param event évènement reçu
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        LOGGER.info("Received a new web socket connection");
        Message<byte[]> message = event.getMessage();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        LOGGER.info(accessor.getFirstNativeHeader("usrID"));
        LOGGER.info(accessor.getSessionId());
        
        Singleton.sessions.put(accessor.getSessionId(),accessor.getFirstNativeHeader("usrID"));
        LOGGER.info(Singleton.sessions.entrySet().toString());
        
    }

    /**
     * Ecoute la déconnexion des sockets
     * @param event évènement reçu
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        LOGGER.info("A web socket is disconnect");
        Message<byte[]> message = event.getMessage();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        LOGGER.info(accessor.getSessionId());
        
        Singleton.sessions.remove(accessor.getSessionId());
        LOGGER.info(Singleton.sessions.entrySet().toString());
    	
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if(username != null) {
//            LOGGER.info("User Disconnected : " + username);
//
//            MessageModel chatMessage = new MessageModel();
//            chatMessage.setType(MessageModel.MessageType.LEAVE);
//            chatMessage.setSender(username);
//
//            messagingTemplate.convertAndSend("/channel/public", chatMessage);
//        }
    }
}
