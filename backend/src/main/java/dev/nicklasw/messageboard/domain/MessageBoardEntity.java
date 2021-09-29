package dev.nicklasw.messageboard.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter(AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class MessageBoardEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_timestamp", nullable = false, updatable = false)
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Bug in SpotBugs. See https://github.com/spotbugs/spotbugs/issues/1601")
    protected LocalDateTime createdTimestamp;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_timestamp")
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Bug in SpotBugs. See https://github.com/spotbugs/spotbugs/issues/1601")
    protected LocalDateTime lastModifiedTimestamp;

    public abstract Long getId();

}
