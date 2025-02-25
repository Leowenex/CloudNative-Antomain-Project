package org.antomain.user_service.service;

import lombok.extern.slf4j.Slf4j;
import org.antomain.user_service.configuration.ApplicationConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@Slf4j
public class ImageService {

    private final ApplicationConfiguration applicationConfiguration;
    private final RestTemplate restTemplate;

    public ImageService(ApplicationConfiguration applicationConfiguration , RestTemplateBuilder restTemplateBuilder) {
        this.applicationConfiguration = applicationConfiguration;
        this.restTemplate = restTemplateBuilder.build();
    }


    public byte[] getImageById(String id) {
        String url = applicationConfiguration.getImageServiceUrl() + "/images/" + id;
        try {
            return restTemplate.getForObject(url, byte[].class);
        } catch (Exception e) {
            log.error("Error while fetching image", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
        }
    }


    public String saveImage(MultipartFile image) {
        String url = applicationConfiguration.getImageServiceUrl() + "/images";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            });

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save image");
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to read image bytes", e);
        }
    }
}
