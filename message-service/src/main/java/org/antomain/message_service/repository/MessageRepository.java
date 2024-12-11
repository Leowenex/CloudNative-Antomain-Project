package org.antomain.message_service.repository;

import org.antomain.message_service.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findBySenderId(UUID senderId);
}
