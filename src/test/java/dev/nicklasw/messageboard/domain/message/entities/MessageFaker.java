package dev.nicklasw.messageboard.domain.message.entities;

import dev.nicklasw.messageboard.domain.EntityFaker;
import org.apache.commons.lang3.RandomUtils;

public class MessageFaker implements EntityFaker<Message> {

    private String text;

    public MessageFaker() {
        this.text = "Text" + RandomUtils.nextInt(1, Integer.MAX_VALUE);
    }

    @Override
    public Message create() {
        return new Message(null, text);
    }

    public static Message random() {
        return message().create();
    }

    public static MessageFaker message() {
        return new MessageFaker();
    }

}
