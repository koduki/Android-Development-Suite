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
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;
    @Inject
    private twitter4j.Twitter twitter;

    public Twitter(){}
    public Twitter(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;

//        ConfigurationBuilder builder  = new ConfigurationBuilder();
//        builder.setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret);
//        builder.setOAuthAccessToken(accessToken);
//
//        builder.setOAuthAccessTokenSecret(accessTokenSecret);
//        this.twitter =  new TwitterFactory(builder.build()).getInstance();
    }

    public Status tweet(String message) {
        try {
            return this.twitter.updateStatus(message);
        } catch (TwitterException e) {
            throw new RuntimeException("fail tweet.", e);
        }
    }
}