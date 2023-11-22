package com.sparta.gamefeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GameFeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameFeedApplication.class, args);
    }

}
