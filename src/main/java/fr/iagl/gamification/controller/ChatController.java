package fr.iagl.gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import fr.iagl.gamification.data.entity.MessageEntity;
import fr.iagl.gamification.data.repository.MessageRepository;
import fr.iagl.gamification.model.MessageModel;

@Controller
public class ChatController {

	@Autowired
	MessageRepository repository;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageModel chatMessage) {
        System.out.println("sendMessage");
        repository.save(new MessageEntity(chatMessage.getSender(), chatMessage.getContent()));
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/channel/public")
    public MessageModel addUser(@Payload MessageModel chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
