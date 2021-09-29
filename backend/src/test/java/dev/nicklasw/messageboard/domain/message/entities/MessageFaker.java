package dev.nicklasw.messageboard.domain.message.entities;

import dev.nicklasw.messageboard.domain.EntityFaker;
import dev.nicklasw.messageboard.domain.user.entities.User;
import dev.nicklasw.messageboard.domain.user.entities.UserFaker;
import org.apache.commons.lang3.RandomUtils;

public class MessageFaker implements EntityFaker<Message> {

    private String text;
    private User user;

    public MessageFaker() {
        this.text = "Text" + RandomUtils.nextInt(1, Integer.MAX_VALUE);
        this.user = UserFaker.random();
    }

    @Override
    public Message create() {
        return new Message(null, text, user);
    }

    public MessageFaker with(final User user) {
        this.user = user;

        return this;
    }

    public static Message random() {
        return message().create();
    }

    public static MessageFaker message() {
        return new MessageFaker();
    }

}
