package dev.nicklasw.messageboard.adapter.driven.persistence;

import java.util.Optional;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import dev.nicklasw.messageboard.domain.message.entities.Message;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends MessageBoardPredicateRepository<Message> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "5000")})
    @Query("select m from Message m where m.id = :id")
    @Transactional
    Optional<Message> findOneForUpdate(final Long id);

}
