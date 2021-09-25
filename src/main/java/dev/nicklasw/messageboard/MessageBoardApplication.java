package dev.nicklasw.messageboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class MessageBoardApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MessageBoardApplication.class, args);
    }

}
