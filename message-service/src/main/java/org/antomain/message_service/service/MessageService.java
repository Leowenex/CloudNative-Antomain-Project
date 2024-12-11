package org.antomain.message_service.service;

import lombok.RequiredArgsConstructor;
import org.antomain.message_service.model.dto.MessageCreationDto;
import org.antomain.message_service.model.entity.Message;
import org.antomain.message_service.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getMessagesBySenderId(UUID senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message saveMessage(MessageCreationDto messageCreationDto) {
        Message message = new Message(messageCreationDto.getSenderId(), messageCreationDto.getContent());
        return messageRepository.save(message);
    }

    public void deleteMessage(UUID messageId) {
        messageRepository.deleteById(messageId);
    }
}
