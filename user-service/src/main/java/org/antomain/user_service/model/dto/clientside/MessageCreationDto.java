package org.antomain.user_service.model.dto.clientside;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antomain.user_service.model.dto.serviceside.AuthoredMessageCreationDto;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageCreationDto {
    private String content;

    public AuthoredMessageCreationDto toAuthoredMessageCreationDto(UUID senderId) {
        return new AuthoredMessageCreationDto(senderId, content);
    }
}
