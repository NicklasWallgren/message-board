package dev.nicklasw.messageboard.domain.user.service;

import java.util.Optional;

import dev.nicklasw.messageboard.adapter.driven.persistence.user.UserRepository;
import dev.nicklasw.messageboard.domain.user.entities.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(@NonNull final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(@NonNull final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User create(@NonNull final User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Invalid username provided " + username));
    }
}
