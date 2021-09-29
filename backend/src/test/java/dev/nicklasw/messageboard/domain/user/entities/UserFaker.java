package dev.nicklasw.messageboard.domain.user.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.nicklasw.messageboard.domain.EntityFaker;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import org.apache.commons.lang3.RandomUtils;

public class UserFaker implements EntityFaker<User> {

    private Long id;
    private String username;
    private String password;
    private final List<Message> messages;

    public UserFaker() {
        this.username = "Username " + RandomUtils.nextInt(1, Integer.MAX_VALUE);
        this.password = "Password " + RandomUtils.nextInt(1, Integer.MAX_VALUE);
        this.messages = new ArrayList<>();
    }

    @Override
    public User create() {
        return new User(id, username, password, Collections.emptyList());
    }

    public UserFaker withId(long id) {
        this.id = id;
        return this;
    }

    public UserFaker withUsername(final String username) {
        this.username = username;

        return this;
    }

    public UserFaker withPassword(final String password) {
        this.password = password;

        return this;
    }

    public static User random() {
        return user().create();
    }

    public static UserFaker user() {
        return new UserFaker();
    }
}
