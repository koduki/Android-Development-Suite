package cn.orz.pascal.android_example.model;

import com.google.inject.Inject;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter Client.
 *
 * @author koduki
 */
public class Twitter {
    @Inject
    private twitter4j.Twitter twitter;

    public Twitter(){}

    public Status tweet(String message) {
        try {
            return this.twitter.updateStatus(message);
        } catch (TwitterException e) {
            throw new RuntimeException("fail tweet.", e);
        }
    }
}