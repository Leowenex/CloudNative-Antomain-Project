package org.antomain.user_service.model.dto.serviceside;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antomain.user_service.model.dto.clientside.EnrichedMessageDto;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private UUID id;
    private UUID senderId;
    private String content;

    public EnrichedMessageDto toEnrichedMessageDto(String senderUsername) {
        return new EnrichedMessageDto(id, senderId, content, senderUsername);
    }
}
