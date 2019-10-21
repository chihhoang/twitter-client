package com.twitter.client.service.impl;

import com.twitter.client.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/** @author choang */
@Service
@Slf4j
public class TweetServiceImpl implements TweetService {
  private Twitter twitter = TwitterFactory.getSingleton();

  @Override
  public Status createTweet(String text) throws TwitterException {
    return twitter.updateStatus(text);
  }

  @Override
  public Status getTweet(long id) throws TwitterException {
    return twitter.showStatus(id);
  }

  @Override
  public Status destroyTweet(long id) throws TwitterException {
    return twitter.destroyStatus(id);
  }

  @Override
  public ResponseList<Status> getUserTimeline() throws TwitterException {

    return twitter.getUserTimeline();
  }

  @Override
  public ResponseList<Status> getUserTimeline(long userId) throws TwitterException {
    return twitter.getUserTimeline(userId);
  }
  
  /***
 * @author Anvitha Karanam
 ***/

  @Override
  public QueryResult searchTwitter(Query query) throws TwitterException {
    return twitter.search(query);
  }
}
