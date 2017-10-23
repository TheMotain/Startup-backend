package fr.iagl.gamification.websocket.config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import fr.iagl.gamification.controller.ChatController;
import fr.iagl.gamification.services.ChatService;

public class WebSocketConfigTest {

    private WebSocketConfig webSocketConfig;

	@Mock
	private StompEndpointRegistry stompEndPointRegistry;

	@Mock
	private MessageBrokerRegistry registry;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.webSocketConfig = new WebSocketConfig();
    }

	@Test
	public void testRegisterStompEndpoints() {
		StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);
		doReturn(registration).when(this.stompEndPointRegistry).addEndpoint("/ws");
		doReturn(registration).when(registration).setAllowedOrigins("*");

		this.webSocketConfig.registerStompEndpoints(this.stompEndPointRegistry);

		verify(registration).withSockJS();
	}

	@Test
	public void testConfigureMessageBroker() {
		this.webSocketConfig.configureMessageBroker(this.registry);

		verify(registry).setApplicationDestinationPrefixes("/app");
		verify(registry).enableSimpleBroker("/channel");
	}

}
