package org.antomain.user_service.model.dto.clientside;

import lombok.Getter;
import lombok.Setter;
import org.antomain.user_service.model.dto.serviceside.MessageDto;

import java.util.UUID;

@Getter
@Setter
public class EnrichedMessageDto extends MessageDto {

    public EnrichedMessageDto(UUID id, UUID senderId, String content, String senderUsername) {
        super(id, senderId, content);
        this.senderUsername = senderUsername;
    }

    private String senderUsername;
}
