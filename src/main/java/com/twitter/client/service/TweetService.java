package com.twitter.client.service;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

public interface TweetService {
  Status createTweet(String text) throws TwitterException;

  Status getTweet(long id) throws TwitterException;

  Status destroyTweet(long id) throws TwitterException;

  ResponseList<Status> getUserTimeline() throws TwitterException;

  ResponseList<Status> getUserTimeline(long userId) throws TwitterException;
  
  QueryResult searchTwitter(Query search) throws TwitterException;
}
