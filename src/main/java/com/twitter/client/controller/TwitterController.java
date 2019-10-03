package com.twitter.client.controller;

import com.twitter.client.service.TweetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class TwitterController {

  private final TweetService tweetService;

  @GetMapping("/tweets")
  public String getTweets() {
    return "";
  }

  @PostMapping("/tweets")
  public Status createTweet(@RequestParam String text) {
    try {
      return tweetService.createTweet(text);
    } catch (TwitterException e) {
      log.error("Error creating tweet. Twitter Service or network unavailable.", e);
      e.printStackTrace();
    }

    return null;
  }

  @GetMapping("/tweets/{id}")
  public Status getTweet(@PathVariable long id) {
    try {
      return tweetService.getTweet(id);
    } catch (TwitterException e) {
      log.error("Error getting tweet {}. Twitter Service or network unavailable.", id, e);
      e.printStackTrace();
    }

    return null;
  }

  @PostMapping("/tweets/{id}")
  public Status destroyTweet(@PathVariable long id) {
    try {
      return tweetService.destroyTweet(id);
    } catch (TwitterException e) {
      log.error("Error deleting tweet {}. Twitter Service or network unavailable.", id, e);
      e.printStackTrace();
    }

    return null;
  }

  @GetMapping("/tweets/userTimeline")
  public ResponseList<Status> getUserTimeline() {
    try {
      return tweetService.getUserTimeline();
    } catch (TwitterException e) {
      log.error("Error getting current user timeline. Twitter Service or network unavailable.", e);
      e.printStackTrace();
    }

    return null;
  }

  @GetMapping("/tweets/userTimeline/{userId}")
  public ResponseList<Status> getUserTimeline(@PathVariable long userId) {
    try {
      return tweetService.getUserTimeline(userId);
    } catch (TwitterException e) {
      log.error(
          "Error getting user {} timeline. Twitter Service or network unavailable.", userId, e);
      e.printStackTrace();
    }

    return null;
  }
}
