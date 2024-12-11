package org.antomain.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.antomain.user_service.model.dto.clientside.EnrichedMessageDto;
import org.antomain.user_service.model.dto.clientside.MessageCreationDto;
import org.antomain.user_service.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<EnrichedMessageDto> postMeassage(@RequestBody MessageCreationDto messageCreationDto) {
        return ResponseEntity.ok(messageService.postMessage(messageCreationDto));
    }

    @GetMapping
    public ResponseEntity<List<EnrichedMessageDto>> getMessages() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<EnrichedMessageDto>> getMessagesByUsername(@PathVariable String username) {
        return ResponseEntity.ok(messageService.getMessagesByUsername(username));
    }

}
