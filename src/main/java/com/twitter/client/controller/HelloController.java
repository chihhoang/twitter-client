package com.twitter.client.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/healthCheck")
  @ResponseStatus(HttpStatus.OK)
  public String healthCheck() {
    return "Health Check OK";
  }
}
