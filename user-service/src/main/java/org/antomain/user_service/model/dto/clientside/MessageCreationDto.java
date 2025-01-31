package org.antomain.user_service.model.dto.clientside;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageCreationDto {
    private String content;
    private MultipartFile messagePicture;
}
