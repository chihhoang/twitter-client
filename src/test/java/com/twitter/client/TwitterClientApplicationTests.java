package com.twitter.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import com.twitter.client.service.TweetService;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TwitterClientApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	TweetService tweetservice;
	
	@Test
	public void createTweet() throws TwitterException {
	  assertNotNull(tweetservice.createTweet("test tweet"));
	}
	
	@Test
	public void getUserTimeline() throws TwitterException {
	  assertNotNull(tweetservice.getUserTimeline());
	}
	
	@Test
	public void destroyTweet() throws TwitterException {		
	  ResponseList<Status> statusResponse = tweetservice.getUserTimeline();
	    if(statusResponse.size() > 0) {
		  //deletes first tweet in the usertimeline
		  Status status = statusResponse.get(0);
		  assertNotNull(tweetservice.destroyTweet(status.getId()));		
	    }
    }
}
