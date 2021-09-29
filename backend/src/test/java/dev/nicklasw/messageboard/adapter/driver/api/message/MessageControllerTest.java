package dev.nicklasw.messageboard.adapter.driver.api.message;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;

import dev.nicklasw.messageboard.ApiIntegrationTest;
import dev.nicklasw.messageboard.WithStatelessUser;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageCreateRequest;
import dev.nicklasw.messageboard.adapter.driver.api.message.requests.MessageUpdateRequest;
import dev.nicklasw.messageboard.domain.message.entities.Message;
import dev.nicklasw.messageboard.domain.message.entities.MessageFactory;
import dev.nicklasw.messageboard.domain.message.entities.MessageFaker;
import dev.nicklasw.messageboard.domain.user.entities.User;
import dev.nicklasw.messageboard.domain.user.entities.UserFactory;
import dev.nicklasw.messageboard.domain.user.entities.UserFaker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class MessageControllerTest extends ApiIntegrationTest {

    @Autowired
    private MessageFactory messageFactory;
    @Autowired
    private UserFactory userFactory;

    @Test
    @WithStatelessUser
    void givenValidMessageId_whenFindById_thenIsOk() throws Exception {
        final Message message = messageFactory.givenAny();

        whenGetThenOk("/api/messages/" + message.getId())
            .andExpect(jsonPath("$.id", Matchers.equalTo(message.getId()), Long.class))
            .andExpect(jsonPath("$.text", Matchers.equalTo(message.getText())));
    }

    @Test
    @WithStatelessUser
    void givenInvalidMessageId_whenFindById_thenIsNotFound() throws Exception {
        whenGet("/api/messages/" + 1, HttpStatus.NOT_FOUND);
    }

    @Test
    @WithStatelessUser
    void givenTwoMessages_whenFindAll_thenIsOk() throws Exception {
        final Message message1 = messageFactory.givenAny();
        final Message message2 = messageFactory.givenAny();

        whenGetThenOk("/api/messages")
            .andExpect(jsonPath("$.content", hasSize(Matchers.equalTo(2))))
            .andExpect(jsonPath("$.content[0].id", Matchers.equalTo(message1.getId()), Long.class))
            .andExpect(jsonPath("$.content[0].text", Matchers.equalTo(message1.getText())))
            .andExpect(jsonPath("$.content[1].id", Matchers.equalTo(message2.getId()), Long.class))
            .andExpect(jsonPath("$.content[1].text", Matchers.equalTo(message2.getText())));
    }

    @Test
    @WithStatelessUser
    void givenNoMessages_whenFindAll_thenIsOk() throws Exception {
        whenGetThenOk("/api/messages")
            .andExpect(jsonPath("$.content", hasSize(Matchers.equalTo(0))));
    }

    @Test
    void givenNewMessage_whenSave_thenIsCreated() throws Exception {
        givenStatefulAuthenticatedUser();

        final MessageCreateRequest request = new MessageCreateRequest("A very descriptive text");

        whenPostThenIsCreated("/api/messages", request)
            .andExpect(jsonPath("$.id", Matchers.isA(Long.class), Long.class))
            .andExpect(jsonPath("$.text", Matchers.equalTo(request.getText())));
    }

    @Test
    @WithStatelessUser
    void givenBlankText_whenSave_thenIsBadRequest() throws Exception {
        final MessageCreateRequest request = new MessageCreateRequest("");

        whenPost("/api/messages", request, HttpStatus.BAD_REQUEST);
    }

    @Test
    @WithStatelessUser
    void givenNullText_whenSave_thenIsBadRequest() throws Exception {
        final MessageCreateRequest request = new MessageCreateRequest(null);

        whenPost("/api/messages", request, HttpStatus.BAD_REQUEST);
    }

    @Test
    void givenMessage_whenUpdate_thenIsOk() throws Exception {
        final User user = givenStatefulAuthenticatedUser();
        final Message message = messageFactory.given(MessageFaker.message().with(user));

        final MessageUpdateRequest request = new MessageUpdateRequest("A very descriptive text");

        whenPatchThenIsOK("/api/messages/" + message.getId(), request)
            .andExpect(jsonPath("$.id", Matchers.isA(Long.class), Long.class))
            .andExpect(jsonPath("$.text", Matchers.equalTo(request.getText())));
    }

    @Test
    @WithStatelessUser(userId = 0)
    void givenMessageFromOtherUser_whenUpdate_thenIsBadRequest() throws Exception {
        final Message message = messageFactory.given(MessageFaker.message()
            .with(givenStatefulUser()));

        final MessageUpdateRequest request = new MessageUpdateRequest("A very descriptive text");

        whenPatch("/api/messages/" + message.getId(), request, HttpStatus.BAD_REQUEST);
    }

    @Test
    @WithStatelessUser
    void givenBlankText_whenUpdate_thenIsBadRequest() throws Exception {
        final Message message = messageFactory.givenAny();

        final MessageUpdateRequest request = new MessageUpdateRequest("");

        whenPatch("/api/messages/" + message.getId(), request, HttpStatus.BAD_REQUEST);
    }

    @Test
    @WithStatelessUser
    void givenNullText_whenUpdate_thenIsBadRequest() throws Exception {
        final Message message = messageFactory.givenAny();

        final MessageUpdateRequest request = new MessageUpdateRequest(null);

        whenPatch("/api/messages/" + message.getId(), request, HttpStatus.BAD_REQUEST);
    }

    @Test
    void givenMessage_whenDelete_thenIsNoContent() throws Exception {
        final User user = givenStatefulAuthenticatedUser();
        final Message message = messageFactory.given(MessageFaker.message()
            .with(user));

        whenDeleteThenIsNoContent("/api/messages/" + message.getId());
    }

    @Test
    @WithStatelessUser(userId = 0)
    void givenMessageFromOtherUser_whenDelete_thenIsBadRequest() throws Exception {
        final Message message = messageFactory.given(MessageFaker.message()
            .with(givenStatefulUser()));

        whenDelete("/api/messages/" + message.getId(), HttpStatus.BAD_REQUEST);
    }

    @Test
    @WithStatelessUser
    void givenNoMessage_whenDelete_thenIsUnprocessableEntity() throws Exception {
        whenDelete("/api/messages/" + 1L, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private User givenStatefulUser() {
        return userFactory.given(UserFaker.user()
            .withUsername("username")
            .withPassword("password")
            .create());
    }

    private User givenStatefulAuthenticatedUser() {
        final User user = givenStatefulUser();

        final Authentication authentication = new UsernamePasswordAuthenticationToken(
            user, user.getPassword(), Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return user;
    }

}