package com.ox.user.controllers;

import com.ox.user.dto.messages.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message deleteClient(@Payload Message message) {
        message.setTimeStamp(new Date());
        return message;
    }
}
