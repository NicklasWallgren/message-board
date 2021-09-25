package dev.nicklasw.messageboard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMessageBoardEntity is a Querydsl query type for MessageBoardEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QMessageBoardEntity extends EntityPathBase<MessageBoardEntity> {

    private static final long serialVersionUID = 1320255830L;

    public static final QMessageBoardEntity messageBoardEntity = new QMessageBoardEntity("messageBoardEntity");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> createdTimestamp = createDateTime("createdTimestamp", java.time.LocalDateTime.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.time.LocalDateTime> lastModifiedTimestamp = createDateTime("lastModifiedTimestamp", java.time.LocalDateTime.class);

    public QMessageBoardEntity(String variable) {
        super(MessageBoardEntity.class, forVariable(variable));
    }

    public QMessageBoardEntity(Path<? extends MessageBoardEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMessageBoardEntity(PathMetadata metadata) {
        super(MessageBoardEntity.class, metadata);
    }

}

