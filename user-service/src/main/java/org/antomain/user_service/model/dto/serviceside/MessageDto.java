package org.antomain.user_service.model.dto.serviceside;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antomain.user_service.model.dto.clientside.EnrichedMessageDto;
import org.antomain.user_service.model.entity.User;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private UUID id;
    private UUID senderId;
    private String content;
    private String messagePictureFilename;

    public EnrichedMessageDto toEnrichedMessageDto(User sender, String imageServiceEndpoint) {
        return new EnrichedMessageDto(id, senderId, content, messagePictureFilename, sender, imageServiceEndpoint);
    }
}
