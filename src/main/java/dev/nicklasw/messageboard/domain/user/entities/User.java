package dev.nicklasw.messageboard.domain.user.entities;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import dev.nicklasw.messageboard.domain.MessageBoardEntity;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "u_username", columnNames = "username"))
public class User extends MessageBoardEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequenceGenerator")
    @SequenceGenerator(name = "report_row_sequenceGenerator", allocationSize = 10, sequenceName = "s_user")
    private Long id;

    @Size(min = 1, max = 255)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String password;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Should be mutable")
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Message> messages = new ArrayList<>();

    /**
     * Creates new {@link User}.
     *
     * @param username must not be {@literal null}.
     * @param password must not be {@literal null}.
     * @return a {@link User}.
     * @throws IllegalArgumentException if {@literal username} is {@literal null} .
     * @throws IllegalArgumentException if {@literal password} is {@literal null} .
     */
    public static User of(@NonNull final String username, @NonNull final String password) {
        return User.of(null, username, password);
    }

    /**
     * Creates new {@link User}.
     *
     * @param id of the user.
     * @param username must not be {@literal null}.
     * @param password must not be {@literal null}.
     * @return a {@link User}.
     * @throws IllegalArgumentException if {@literal username} is {@literal null} .
     * @throws IllegalArgumentException if {@literal password} is {@literal null} .
     */
    public static User of(final Long id, @NonNull final String username, @NonNull final String password) {
        return new User(id, username, password, new ArrayList<>());
    }

    @SuppressWarnings("EqualsGetClass")
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
