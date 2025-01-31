package org.antomain.message_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageCreationDto {

    private UUID senderId;
    private String content;
    private String messagePictureFilename;
}
