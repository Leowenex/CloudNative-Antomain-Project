package org.antomain.message_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID senderId;

    private String content;

    public Message(UUID senderId, String content) {
        this.senderId = senderId;
        this.content = content;
    }
}
