package com.twitter.client.service.impl;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceImplTest {

  @Mock private Twitter twitter;
  @InjectMocks private TweetServiceImpl tweetService;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void createTweet() throws TwitterException {
    String status = "{\"text\": \"My test tweet\"}";

    when(tweetService.createTweet(status)).thenReturn(TwitterObjectFactory.createStatus(status));

    tweetService.createTweet(status);
  }

  @Test
  public void getTweet() {}

  @Test
  public void destroyTweet() {}

  @Test
  public void getUserTimeline() {}

  @Test
  public void testGetUserTimeline() {}
}
