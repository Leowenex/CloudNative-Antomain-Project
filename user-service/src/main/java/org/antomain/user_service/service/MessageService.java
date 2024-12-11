package org.antomain.user_service.service;

import org.antomain.user_service.model.dto.clientside.EnrichedMessageDto;
import org.antomain.user_service.model.dto.serviceside.AuthoredMessageCreationDto;
import org.antomain.user_service.model.dto.clientside.MessageCreationDto;
import org.antomain.user_service.model.dto.serviceside.MessageDto;
import org.antomain.user_service.model.entity.User;
import org.antomain.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final RestTemplate restTemplate;
    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${services.messageService.url}")
    private String messageServiceUrl;

    public MessageService(RestTemplateBuilder restTemplateBuilder, UserService userService, UserRepository userRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public EnrichedMessageDto postMessage(MessageCreationDto messageCreationDto) {
        User currentUser = userService.getCurrentUser();
        AuthoredMessageCreationDto authoredMessageCreationDto = messageCreationDto.toAuthoredMessageCreationDto(currentUser.getId());
        MessageDto messageDto = restTemplate.postForObject(messageServiceUrl + "/messages", authoredMessageCreationDto, MessageDto.class);
        assert messageDto != null;
        return messageDto.toEnrichedMessageDto(currentUser.getUsername());
    }

    // May need to be sent to own class
    private List<EnrichedMessageDto> enrichMessages(MessageDto[] messages) {
        HashMap<UUID, String> userCache = new HashMap<>();
        return Arrays.stream(messages)
                .map(message -> {
                    String senderUsername = userCache.computeIfAbsent(
                            message.getSenderId(),
                            id -> userRepository.findById(id).map(User::getUsername).orElse("?")
                    );
                    return message.toEnrichedMessageDto(senderUsername);
                })
                .collect(Collectors.toList());
    }

    public List<EnrichedMessageDto> getMessages() {
        MessageDto[] messages = restTemplate.getForObject(messageServiceUrl + "/messages", MessageDto[].class);
        assert messages != null;
        return enrichMessages(messages);
    }

    public List<EnrichedMessageDto> getMessagesByUsername(String username) {
        UUID userId = userService.getUserByUsername(username).getId();
        MessageDto[] messages = restTemplate.getForObject(messageServiceUrl + "/messages/" + userId, MessageDto[].class);
        assert messages != null;
        return enrichMessages(messages); // Possible optimisation by prefilling the userCache with the current user
    }

}
