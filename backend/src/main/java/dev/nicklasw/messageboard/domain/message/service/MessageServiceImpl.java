package dev.nicklasw.messageboard.domain.message.service;

import java.util.Optional;

import com.querydsl.core.types.Predicate;
import dev.nicklasw.messageboard.adapter.driven.persistence.message.MessageRepository;
import dev.nicklasw.messageboard.adapter.driven.security.AuthenticationService;
import dev.nicklasw.messageboard.adapter.driver.api.common.exception.MissingEntityException;
import dev.nicklasw.messageboard.adapter.driver.api.common.exception.NotSupportedException;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import dev.nicklasw.messageboard.domain.user.entities.User;
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
    private final AuthenticationService authenticationService;

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
    @Transactional
    public Message create(@NonNull final Message message) {
        messageRepository.save(message);

        return message;
    }

    @Override
    @Transactional
    public Message update(@NonNull final Message message) {
        final User user = authenticationService.optionalAuthenticatedUser()
            .orElseThrow();

        if (message.isNotOwner(user)) {
            throw new NotSupportedException("You can only update your own messages");
        }

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
    @Transactional
    public Message delete(@NonNull final Message message) {
        final User user = authenticationService.optionalAuthenticatedUser()
            .orElseThrow();

        if (message.isNotOwner(user)) {
            throw new NotSupportedException("You can only delete your own messages");
        }

        messageRepository.delete(message);

        return message;
    }

}
