package dev.nicklasw.messageboard.domain.user.entities;

import dev.nicklasw.messageboard.adapter.driven.persistence.user.UserRepository;
import dev.nicklasw.messageboard.domain.EntityFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory extends EntityFactory<User, UserFaker> {

    private final UserRepository userRepository;

    @Override
    public User givenAny() {
        return given(UserFaker.user());
    }

    @Override
    public User given(@NonNull final UserFaker faker) {
        return given(faker.create());
    }

    @Override
    public User given(@NonNull final User entity) {
        return userRepository.save(entity);
    }

}
