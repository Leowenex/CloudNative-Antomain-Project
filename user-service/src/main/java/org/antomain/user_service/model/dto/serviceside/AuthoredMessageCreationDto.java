package org.antomain.user_service.model.dto.serviceside;

import lombok.Getter;
import lombok.Setter;
import org.antomain.user_service.model.dto.clientside.MessageCreationDto;

import java.util.UUID;

@Getter
@Setter
public class AuthoredMessageCreationDto extends MessageCreationDto {

    public AuthoredMessageCreationDto(UUID senderId, String message){
        super(message);
        this.senderId = senderId;
    }

    private UUID senderId;
}
