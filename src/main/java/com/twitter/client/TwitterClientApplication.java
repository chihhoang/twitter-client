package com.twitter.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@Slf4j
@CrossOrigin
public class TwitterClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(TwitterClientApplication.class, args);
  }

  @Bean
  public CommandLineRunner startup() {
    return (String... args) -> {
      log.info("Starting up...");
    };
  }
}
