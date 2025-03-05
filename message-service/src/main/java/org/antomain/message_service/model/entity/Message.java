package org.antomain.message_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;

    private UUID senderId;

    private String content;

    private String messagePictureFilename;

    public Message(UUID senderId, String content, String messagePictureFilename) {
        this.senderId = senderId;
        this.content = content;
        this.messagePictureFilename = messagePictureFilename;
    }
}
