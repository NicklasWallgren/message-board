package dev.nicklasw.messageboard.adapter.driver.api.message.converters;

import dev.nicklasw.messageboard.adapter.driven.security.AuthenticationService;
import dev.nicklasw.messageboard.adapter.driver.api.common.converter.ApiRequestCreateConverter;
import dev.nicklasw.messageboard.adapter.driver.api.common.converter.ApiRequestUpdateConverter;
import dev.nicklasw.messageboard.adapter.driver.api.common.converter.ApiResponseConverter;
import dev.nicklasw.messageboard.adapter.driver.api.common.exception.NotFoundException;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageCreateRequest;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageUpdateRequest;
import dev.nicklasw.messageboard.adapter.driver.api.message.responses.MessageResponse;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import dev.nicklasw.messageboard.domain.message.service.MessageService;
import dev.nicklasw.messageboard.domain.user.entities.User;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageApiConverter implements
    ApiResponseConverter<Message, MessageResponse>,
    ApiRequestCreateConverter<Message, MessageCreateRequest>,
    ApiRequestUpdateConverter<Message, MessageUpdateRequest> {

    private final AuthenticationService authenticationService;

    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Bug in SpotBugs. See https://github.com/spotbugs/spotbugs/issues/1601")
    private final MessageService messageService;

    @Override
    public MessageResponse responseOf(@NonNull final Message message) {
        final User user = message.getUser();

        return MessageResponse.of(message.getId(), message.getSubject(), message.getText(), MessageResponse.User.of(user.getId(), user.getUsername()));
    }

    @Override
    public Message toDomain(@NonNull final MessageCreateRequest request) {
        final User user = authenticationService.optionalAuthenticatedUser().orElseThrow();

        return Message.of(request.getSubject(), request.getText(), user);
    }

    @Override
    public Message updatedDomain(@NonNull final Long id, @NonNull final MessageUpdateRequest request) {
        final Message existing = messageService.findOneForUpdate(id)
            .orElseThrow(() -> NotFoundException.of("message", "id", id));

        return existing.setSubject(request.getSubject())
            .setText(request.getText());
    }
}
