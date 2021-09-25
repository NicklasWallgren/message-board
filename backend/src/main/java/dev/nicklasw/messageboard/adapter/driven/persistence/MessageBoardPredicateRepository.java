package dev.nicklasw.messageboard.adapter.driven.persistence;

import dev.nicklasw.messageboard.domain.MessageBoardEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MessageBoardPredicateRepository<E extends MessageBoardEntity> extends MessageBoardRepository<E>, QuerydslPredicateExecutor<E> {
}
