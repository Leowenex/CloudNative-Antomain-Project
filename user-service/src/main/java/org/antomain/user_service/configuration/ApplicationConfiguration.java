package org.antomain.user_service.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Getter
@Setter
public class ApplicationConfiguration {

    @Value("${services.messageService.url}")
    private String messageServiceUrl;

    @Value("${services.imagesService.url}")
    private String imageServiceUrl;

    @Value("${services.userService.images.url}")
    private String userServiceImagesEndpoint;
}
