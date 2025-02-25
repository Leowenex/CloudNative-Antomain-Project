package org.antomain.user_service.service;

import org.antomain.user_service.configuration.ApplicationConfiguration;
import org.antomain.user_service.model.dto.clientside.EnrichedMessageDto;
import org.antomain.user_service.model.dto.serviceside.AuthoredMessageCreationDto;
import org.antomain.user_service.model.dto.clientside.MessageCreationDto;
import org.antomain.user_service.model.dto.serviceside.MessageDto;
import org.antomain.user_service.model.entity.User;
import org.antomain.user_service.repository.UserRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final ApplicationConfiguration applicationConfiguration;
    private final RestTemplate restTemplate;
    private final ImageService imageService;
    private final UserService userService;
    private final UserRepository userRepository;


    public MessageService(ApplicationConfiguration applicationConfiguration ,RestTemplateBuilder restTemplateBuilder, UserService userService, UserRepository userRepository, ImageService imageService) {
        this.applicationConfiguration = applicationConfiguration;
        this.restTemplate = restTemplateBuilder.build();
        this.userService = userService;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public EnrichedMessageDto postMessage(MessageCreationDto messageCreationDto) {
        User currentUser = userService.getCurrentUser();
        String messagePictureFilename = uploadMessagePicture(messageCreationDto.getMessagePicture());
        AuthoredMessageCreationDto authoredMessageCreationDto = new AuthoredMessageCreationDto(currentUser.getId(), messageCreationDto.getContent(), messagePictureFilename);
        MessageDto messageDto = restTemplate.postForObject(applicationConfiguration.getMessageServiceUrl() + "/messages", authoredMessageCreationDto, MessageDto.class);
        assert messageDto != null;
        return messageDto.toEnrichedMessageDto(currentUser, applicationConfiguration.getImageServiceUrl());
    }

    // May need to be sent to own class
    private List<EnrichedMessageDto> enrichMessages(MessageDto[] messages) {
        HashMap<UUID, User> userCache = new HashMap<>();
        return Arrays.stream(messages)
                .map(message -> {
                    User sender = userCache.computeIfAbsent(message.getSenderId(), id -> userRepository.findById(id).orElseThrow());
                    return message.toEnrichedMessageDto(sender, applicationConfiguration.getImageServiceUrl());
                })
                .collect(Collectors.toList());
    }

    public List<EnrichedMessageDto> getMessages() {
        MessageDto[] messages = restTemplate.getForObject(applicationConfiguration.getMessageServiceUrl() + "/messages", MessageDto[].class);
        assert messages != null;
        return enrichMessages(messages);
    }

    public List<EnrichedMessageDto> getMessagesByUsername(String username) {
        UUID userId = userService.getUserByUsername(username).getId();
        MessageDto[] messages = restTemplate.getForObject(applicationConfiguration.getMessageServiceUrl() + "/messages/" + userId, MessageDto[].class);
        assert messages != null;
        return enrichMessages(messages); // Possible optimisation by prefilling the userCache with the current user
    }

    public String uploadMessagePicture(MultipartFile image) {
        if (image == null) {
            return null;
        }
        return imageService.saveImage(image);
    }
}
