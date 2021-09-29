package dev.nicklasw.messageboard.domain.message.entities;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

import java.util.Objects;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import dev.nicklasw.messageboard.domain.MessageBoardEntity;
import dev.nicklasw.messageboard.domain.user.entities.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Cache;

@Getter
@Entity
@Cache(usage = READ_WRITE)
@Cacheable
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "messages")
public class Message extends MessageBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @SuppressWarnings("EqualsGetClass")
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Message message = (Message) o;
        return Objects.equals(getId(), message.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public boolean isOwner(final User user) {
        return this.user.equals(user);
    }

    public boolean isNotOwner(final User user) {
        return !isOwner(user);
    }

    /**
     * Creates new {@link Message}.
     *
     * @param text must not be {@literal null}.
     * @param user must not be {@literal null}.
     * @return a {@link Message}.
     * @throws IllegalArgumentException if {@literal text} is {@literal null} .
     * @throws IllegalArgumentException if {@literal user} is {@literal null} .
     */
    public static Message of(@NonNull final String text, @NonNull final User user) {
        return new Message(null, text, user);
    }

}
