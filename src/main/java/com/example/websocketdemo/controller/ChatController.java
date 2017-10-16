package com.example.websocketdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.websocketdemo.model.ChatMessage;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {

	@Autowired
	MessageRepository repository;
	
  @Autowired
  private SimpMessagingTemplate brokerMessagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("sendMessage");
        repository.save(new MessageEntity(chatMessage.getSender(), chatMessage.getContent()));
        sendMessage2(chatMessage);
    }

    public void sendMessage2(@Payload ChatMessage chatMessage) {
        System.out.println("sendMessage2");
        this.brokerMessagingTemplate.convertAndSend("/channel/public", chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/channel/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
