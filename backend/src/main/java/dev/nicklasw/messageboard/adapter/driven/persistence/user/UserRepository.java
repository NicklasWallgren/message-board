package dev.nicklasw.messageboard.adapter.driven.persistence.user;

import java.util.Optional;

import dev.nicklasw.messageboard.adapter.driven.persistence.MessageBoardRepository;
import dev.nicklasw.messageboard.domain.user.entities.User;

public interface UserRepository extends MessageBoardRepository<User> {

    Optional<User> findByUsername(final String username);

}
