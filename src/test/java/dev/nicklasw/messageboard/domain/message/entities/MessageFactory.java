package dev.nicklasw.messageboard.domain.message.entities;

import dev.nicklasw.messageboard.adapter.driven.persistence.message.MessageRepository;
import dev.nicklasw.messageboard.domain.EntityFactory;
import dev.nicklasw.messageboard.domain.user.entities.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.util.annotation.NonNull;

@RequiredArgsConstructor
@Component
public class MessageFactory extends EntityFactory<Message, MessageFaker> {

    private final MessageRepository messageRepository;
    private final UserFactory userFactory;

    @Override
    public Message givenAny() {
        return given(MessageFaker.random());
    }

    @Override
    public Message given(@NonNull final MessageFaker faker) {
        return given(faker.create());
    }

    @Override
    public Message given(@NonNull final Message entity) {
        userFactory.given(entity.getUser());

        return messageRepository.save(entity);
    }
}
