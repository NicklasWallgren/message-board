package dev.nicklasw.messageboard;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
public class ApiIntegrationTest extends IntegrationTest {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> ResultActions whenGetThenOk(final String url) throws Exception {
        return whenGet(url, HttpStatus.OK);
    }

    protected ResultActions whenGet(final String url, final HttpStatus expectedStatus) throws Exception {
        return mvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is(expectedStatus.value()));
    }

    protected <I> ResultActions whenPostThenIsCreated(final String url, final I request) throws Exception {
        return mvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is(HttpStatus.CREATED.value()));
    }

    protected <I> ResultActions whenPost(final String url, final I request, final HttpStatus expectedStatus) throws Exception {
        return mvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is(expectedStatus.value()));
    }

    protected <I> ResultActions whenPatchThenIsOK(final String url, final I request) throws Exception {
        return mvc.perform(patch(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is(HttpStatus.OK.value()));
    }

    protected <I> ResultActions whenPatch(final String url, final I request, final HttpStatus expectedStatus) throws Exception {
        return mvc.perform(patch(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is(expectedStatus.value()));
    }

    protected void whenDeleteThenIsNoContent(final String url) throws Exception {
        mvc.perform(delete(url))
            .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
            .andReturn()
            .getResponse().getContentAsString();
    }

    protected void whenDelete(final String url, final HttpStatus expectedStatus) throws Exception {
        mvc.perform(delete(url))
            .andExpect(status().is(expectedStatus.value()))
            .andReturn()
            .getResponse().getContentAsString();
    }


}
