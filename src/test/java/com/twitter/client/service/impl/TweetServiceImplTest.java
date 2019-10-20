package com.twitter.client.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

/** @author choang */
@RunWith(MockitoJUnitRunner.class)
public class TweetServiceImplTest {
  private static final String STATUS = "{\"text\": \"My test tweet\"}";
  private final long tweetId = 123L;

  @Mock private Twitter twitter;
  @Mock ResponseList<Status> statusList;

  @InjectMocks private TweetServiceImpl tweetService;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void createTweet() throws TwitterException {
    when(tweetService.createTweet(STATUS)).thenReturn(TwitterObjectFactory.createStatus(STATUS));

    Status actual = tweetService.createTweet(STATUS);

    assertEquals("My test tweet", actual.getText());
  }

  @Test
  public void getTweet() throws TwitterException {
    when(tweetService.getTweet(tweetId)).thenReturn(TwitterObjectFactory.createStatus(STATUS));

    Status actual = tweetService.getTweet(tweetId);

    assertEquals("My test tweet", actual.getText());
  }

  @Test
  public void destroyTweet() throws TwitterException {
    when(tweetService.destroyTweet(tweetId)).thenReturn(TwitterObjectFactory.createStatus(STATUS));

    Status actual = tweetService.destroyTweet(tweetId);

    assertEquals("My test tweet", actual.getText());
  }

  @Test
  public void getUserTimeline() throws TwitterException {
    when(tweetService.getUserTimeline(tweetId)).thenReturn(statusList);
    when(statusList.get(0)).thenReturn(TwitterObjectFactory.createStatus(STATUS));

    ResponseList<Status> actual = tweetService.getUserTimeline(tweetId);

    assertEquals("My test tweet", actual.get(0).getText());
  }
}
