package dev.nicklasw.messageboard.domain.message.service;

import java.util.Optional;

import com.querydsl.core.types.Predicate;
import dev.nicklasw.messageboard.adapter.driven.persistence.MessageRepository;
import dev.nicklasw.messageboard.adapter.driver.api.exception.MissingEntityException;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Page<Message> findAll(@NonNull final Predicate predicate, @NonNull final Pageable pageable) {
        return messageRepository.findAll(predicate, pageable);
    }

    @Override
    public Optional<Message> findById(@NonNull final Long id) {
        return messageRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Message> findOneForUpdate(@NonNull final Long id) {
        return messageRepository.findOneForUpdate(id);
    }

    @Override
    public Message create(@NonNull final Message message) {
        // TODO, validation
        // TODO, push event

        messageRepository.save(message);

        return message;
    }

    @Override
    public Message update(@NonNull final Message message) {
        // TODO, validation, should only be able to updated it owns messages
        // TODO, push event

        messageRepository.save(message);

        return message;
    }

    @Override
    @Transactional
    public Message delete(@NonNull final Long id) {
        return findOneForUpdate(id)
            .map(this::delete)
            .orElseThrow(() -> MissingEntityException.of(Message.class, id));
    }

    @Override
    //    @Retryable(StaleStateException.class) // TODO
    @Transactional
    public Message delete(@NonNull final Message message) {
        // TODO, validation, should only be able to delete its own messages

        messageRepository.delete(message);

        //        applicationEventPublisher.publishEvent(new SenderEvent(DELETED, sender));

        return message;
    }

}
