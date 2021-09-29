package dev.nicklasw.messageboard.domain;

public interface EntityFaker<E extends MessageBoardEntity> {
    E create();
}
