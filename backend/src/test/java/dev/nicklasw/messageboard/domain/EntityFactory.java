package dev.nicklasw.messageboard.domain;

public abstract class EntityFactory<E extends MessageBoardEntity, F extends EntityFaker<E>> {
    public abstract E givenAny();

    public abstract E given(final F faker);

    public abstract E given(final E entity);

}
