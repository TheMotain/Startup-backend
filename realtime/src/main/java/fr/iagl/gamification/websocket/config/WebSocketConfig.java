package fr.iagl.gamification.websocket.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import fr.iagl.gamification.utils.ChannelEnum;

/**
 * Configuration pour l'utilisation de web socket
 * @author ALEX
 */
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan({ "fr.iagl.gamification.websocket.config", "fr.iagl.gamification.listener.akka",
		"fr.iagl.gamification.listener" })
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	/**
	 * URL pour la connexion d'une web socket
	 */
	private static final String WEBSOCKET_ROOT_CONNECTING = "/ws";

	/**
	 * URL de dialogue root pour la réceptiondes messages du client
	 */
	private static final String WEBSOCKET_ROOT_RECEIVE = "/app";

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.socket.config.annotation.
	 * WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.
	 * web.socket.config.annotation.StompEndpointRegistry)
	 * 
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(WEBSOCKET_ROOT_CONNECTING).setAllowedOrigins("*").withSockJS();
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.socket.config.annotation.
	 * AbstractWebSocketMessageBrokerConfigurer#configureMessageBroker(org.
	 * springframework.messaging.simp.config.MessageBrokerRegistry)
	 * 
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes(WEBSOCKET_ROOT_RECEIVE);
		registry.enableSimpleBroker(ChannelEnum.ROOT_CHANNEL);
	}

}
