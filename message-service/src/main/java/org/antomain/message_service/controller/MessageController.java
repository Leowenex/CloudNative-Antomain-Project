package org.antomain.message_service.controller;

import lombok.RequiredArgsConstructor;
import org.antomain.message_service.model.dto.MessageCreationDto;
import org.antomain.message_service.model.entity.Message;
import org.antomain.message_service.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/{senderId}")
    public ResponseEntity<List<Message>> getMessagesBySenderId(@PathVariable UUID senderId) {
        return ResponseEntity.ok(messageService.getMessagesBySenderId(senderId));
    }

    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody MessageCreationDto message) {
        if (message.getSenderId() == null || message.getContent() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(messageService.saveMessage(message));
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable UUID messageId) {
        messageService.deleteMessage(messageId);
        return ResponseEntity.noContent().build();
    }

}
