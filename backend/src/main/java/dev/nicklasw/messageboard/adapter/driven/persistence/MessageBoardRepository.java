package dev.nicklasw.messageboard.adapter.driven.persistence;

import dev.nicklasw.messageboard.domain.MessageBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MessageBoardRepository<E extends MessageBoardEntity> extends JpaRepository<E, Long> {
}
