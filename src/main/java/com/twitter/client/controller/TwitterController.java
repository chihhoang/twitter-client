package com.twitter.client.controller;

import com.twitter.client.service.TweetService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

/** @author choang */
/**
 * Code changes for getUserTimeline and destroyTweet API's
 * @author manishayacham
 **/
/***
 * @author Anvitha Karanam
 * added Search API functionality to fetch 10 latest tweets containing the 'user-specified-keyword'.  
 ***/
@RestController
@RequestMapping("/api/tweets")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TwitterController {

  private final TweetService tweetService;

  @PostMapping
  public Status createTweet(@RequestParam String text) {
    try {
      return tweetService.createTweet(text);
    } catch (TwitterException e) {
      log.error("Error creating tweet. Twitter Service or network unavailable.", e);
      e.printStackTrace();
    }

    return null;
  }

  @GetMapping("/{id}")
  public Status getTweet(@PathVariable long id) {
    try {
      return tweetService.getTweet(id);
    } catch (TwitterException e) {
      log.error("Error getting tweet {}. Twitter Service or network unavailable.", id, e);
      e.printStackTrace();
    }

    return null;
  }

  @DeleteMapping("/{id}")
  public Status destroyTweet(@PathVariable String id) {
    try {
      return tweetService.destroyTweet(Long.valueOf(id));
    } catch (TwitterException e) {
      log.error("Error deleting tweet {}. Twitter Service or network unavailable.", id, e);
      e.printStackTrace();
    }

    return null;
  }
  
  @GetMapping("/userTimeline")
  public ArrayList<HashMap<String, Object>> getUserTimeline() {
    try {
      ArrayList<HashMap<String, Object>> tweets = new ArrayList<>();

      for (Status s : tweetService.getUserTimeline()) {
        HashMap<String, Object> tweet = new HashMap<>();
        tweet.put("id", String.valueOf(s.getId()));
        tweet.put("name", s.getUser().getName());
        tweet.put("screenName", s.getUser().getScreenName());
        tweet.put("text", s.getText());
        tweets.add(tweet);
      }

      return tweets;
    } catch (TwitterException e) {
      log.error("Error getting current user timeline. Twitter Service or network unavailable.", e);
      e.printStackTrace();
    }

    return null;
  }

  @GetMapping("/userTimeline/{userId}")
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

  @GetMapping("/send")
  public List<String> searchForUserTweets(@RequestParam String searchString) {
    try {
      Logger logger = Logger.getLogger(TwitterController.class);
      Query query = new Query(searchString);
      query.setCount(10);
      query.setLang("en");
      QueryResult result = tweetService.searchTwitter(query);

      List<Status> statusList = result.getTweets();
      List<String> tweets = new ArrayList<>();

      for (Status status : statusList) {
        tweets.add(status.getText());
        logger.info("Adding tweet : " + status.getText());
      }
      return tweets;
    } catch (TwitterException e) {
      log.error("Error creating tweet. Twitter Service or network unavailable.", e);
      e.printStackTrace();
      return null;
    }
  }
}
