package fr.iagl.gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import fr.iagl.gamification.model.MessageModel;
import fr.iagl.gamification.services.ChatService;

@Controller
public class ChatController {

	@Autowired
	private ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageModel chatMessage) {
        chatService.saveMessage(chatMessage);
    }
	
}
