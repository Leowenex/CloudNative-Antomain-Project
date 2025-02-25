package org.antomain.user_service.model.dto.clientside;

import lombok.Getter;
import lombok.Setter;
import org.antomain.user_service.model.entity.User;

import java.util.UUID;

@Getter
@Setter
public class EnrichedMessageDto {

    public EnrichedMessageDto(UUID id, UUID senderId, String content, String messagePictureFilename, User sender, String imageServiceEndpoint) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.messagePictureUrl = imageServiceEndpoint + messagePictureFilename;
        this.senderUsername = sender.getUsername();
        this.senderProfilePictureUrl = imageServiceEndpoint + sender.getProfilePictureFilename();
    }

    private UUID id;
    private UUID senderId;
    private String content;
    private String messagePictureUrl;
    private String senderUsername;
    private String senderProfilePictureUrl;
}
