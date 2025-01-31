package org.antomain.user_service.model.dto.clientside;

import lombok.Getter;
import lombok.Setter;
import org.antomain.user_service.model.dto.serviceside.MessageDto;
import org.antomain.user_service.model.entity.User;

import java.util.UUID;

@Getter
@Setter
public class EnrichedMessageDto extends MessageDto {

    public EnrichedMessageDto(UUID id, UUID senderId, String content, String messagePictureFilename, User sender) {
        super(id, senderId, content, messagePictureFilename);
        this.senderUsername = sender.getUsername();
        this.senderProfilePictureFilename = sender.getProfilePictureFilename();
    }

    private String senderUsername;
    private String senderProfilePictureFilename;
}
