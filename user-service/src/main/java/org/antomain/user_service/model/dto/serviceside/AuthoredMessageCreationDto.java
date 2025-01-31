package org.antomain.user_service.model.dto.serviceside;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AuthoredMessageCreationDto {

    private UUID senderId;
    private String content;
    private String messagePictureFilename;
}
