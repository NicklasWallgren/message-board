package dev.nicklasw.messageboard.adapter.driver.api.converter;

import dev.nicklasw.messageboard.adapter.driver.api.exception.NotFoundClientException;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageCreateRequest;
import dev.nicklasw.messageboard.adapter.driver.api.message.responses.MessageResponse;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageUpdateRequest;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import dev.nicklasw.messageboard.domain.message.service.MessageService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageApiConverter implements
    ApiResponseConverter<Message, MessageResponse>,
    ApiRequestCreateConverter<Message, MessageCreateRequest>,
    ApiRequestUpdateConverter<Message, MessageUpdateRequest> {

    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Bug in SpotBugs. See https://github.com/spotbugs/spotbugs/issues/1601")
    private final MessageService messageService;

    @Override
    public MessageResponse responseOf(@NonNull final Message message) {
        return MessageResponse.of(message.getId(), message.getText());
    }

    @Override
    public Message toDomain(@NonNull final MessageCreateRequest request) {
        return Message.of(request.getText());
    }

    @Override
    public Message updatedDomain(@NonNull final Long id, final MessageUpdateRequest request) {
        final Message existing = messageService.findOneForUpdate(id)
            .orElseThrow(() -> NotFoundClientException.of("message", "id", id));

        return existing.setText(request.getText());
    }
}
