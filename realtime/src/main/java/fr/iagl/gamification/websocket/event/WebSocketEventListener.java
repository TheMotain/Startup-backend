package fr.iagl.gamification.websocket.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

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
    }

    /**
     * Ecoute la déconnexion des sockets
     * @param event évènement reçu
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        LOGGER.info("A web socket is disconnect");
    }
}
