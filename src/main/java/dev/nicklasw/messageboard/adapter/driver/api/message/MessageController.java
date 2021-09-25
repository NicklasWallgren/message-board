package dev.nicklasw.messageboard.adapter.driver.api.message;

import javax.validation.Valid;

import com.querydsl.core.types.Predicate;
import dev.nicklasw.messageboard.adapter.driver.api.annotation.ApiController;
import dev.nicklasw.messageboard.adapter.driver.api.annotation.ApiDelete;
import dev.nicklasw.messageboard.adapter.driver.api.annotation.ApiGet;
import dev.nicklasw.messageboard.adapter.driver.api.annotation.ApiPatch;
import dev.nicklasw.messageboard.adapter.driver.api.annotation.ApiPost;
import dev.nicklasw.messageboard.adapter.driver.api.annotation.SecureOperation;
import dev.nicklasw.messageboard.adapter.driver.api.annotation.SecurePageableOperation;
import dev.nicklasw.messageboard.adapter.driver.api.converter.MessageApiConverter;
import dev.nicklasw.messageboard.adapter.driver.api.exception.NotFoundClientException;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageCreateRequest;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageUpdateRequest;
import dev.nicklasw.messageboard.adapter.driver.api.message.responses.MessageResponse;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import dev.nicklasw.messageboard.domain.message.service.MessageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@ApiController("/api/messages")
@Tag(name = "Messages", description = "Operations concerning messages")
public class MessageController {

    private final MessageService messageService;
    private final MessageApiConverter apiConverter;

    @ApiGet
    @SecurePageableOperation(summary = "Retrieve a list of available messages")
    public Page<MessageResponse> findAll(@QuerydslPredicate(root = Message.class) final Predicate predicate,
                                         @Parameter(hidden = true) @PageableDefault(sort = "id") final Pageable pageable) {
        return messageService.findAll(predicate, pageable)
            .map(apiConverter::responseOf);
    }

    @ApiGet("/{id}")
    @SecureOperation(summary = "Retrieve message by id")
    public MessageResponse findById(@PathVariable("id") final Long id) {
        return messageService.findById(id)
            .map(apiConverter::responseOf)
            .orElseThrow(() -> NotFoundClientException.of("message", "id", id));
    }

    @ApiPost(code = HttpStatus.CREATED)
    @SecureOperation(summary = "Creates a new message.")
    public MessageResponse save(@Parameter(description = "Information about a new message to be created.")
                                @Valid @RequestBody final MessageCreateRequest request) {
        final Message message = apiConverter.toDomain(request);
        return apiConverter.responseOf(messageService.create(message));
    }

    @ApiPatch("/{id}")
    @SecureOperation(summary = "Updates a message existing message.")
    public MessageResponse update(@PathVariable("id") final long id,
                                  @Valid @Parameter(description = "Information about a message to be updated.")
                                  @RequestBody final MessageUpdateRequest request) {
        final Message message = apiConverter.updatedDomain(id, request);
        return apiConverter.responseOf(messageService.update(message));
    }

    @ApiDelete("/{id}")
    @SecureOperation(summary = "Deletes a existing message.")
    public void delete(@Parameter(description = "Id of the message to be deleted. Cannot be empty.") @PathVariable final long id) {
        messageService.delete(id);
    }
}
