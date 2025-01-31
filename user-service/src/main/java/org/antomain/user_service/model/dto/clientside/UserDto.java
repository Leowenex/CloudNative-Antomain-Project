package org.antomain.user_service.model.dto.clientside;

import lombok.Getter;
import org.antomain.user_service.model.entity.User;

import java.util.UUID;

@Getter
public class UserDto {

    private final UUID id;
    private final String username;
    private final String profilePictureFilename;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.profilePictureFilename = user.getProfilePictureFilename();
    }
}
