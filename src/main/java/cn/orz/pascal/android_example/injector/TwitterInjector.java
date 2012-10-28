package cn.orz.pascal.android_example.injector;

import android.util.Log;
import cn.orz.pascal.android_example.config.Config;
import cn.orz.pascal.android_example.config.Environment.Profile;
import com.google.inject.AbstractModule;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

/**
 * TwitterInjector.
 */
public class TwitterInjector extends AbstractModule {
    Profile profile;

    public TwitterInjector(Profile profile) {
        this.profile = profile;
    }

    @Override
    protected void configure() {
        if (this.profile == Profile.PROD) {
            bindProdInjector();
        } else {
            bindMockInjector();
        }
    }

    private void bindMockInjector() {
        try {
            twitter4j.Twitter twitter = mock(twitter4j.Twitter.class);
            when(twitter.updateStatus(anyString())).thenAnswer(new Answer() {
                @Override
                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                    Object[] args = invocationOnMock.getArguments();
                    String message = (String) args[0];
                    System.out.println("tweet message:" + message);

                    Status status = mock(Status.class);
                    User user = mock(User.class);
                    when(status.getText()).thenReturn(message);
                    when(status.getUser()).thenReturn(user);
                    when(user.getScreenName()).thenReturn("koduki");
                    return status;
                }
            });
            bind(twitter4j.Twitter.class).toInstance(twitter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void bindProdInjector() {
        try {
            String consumerKey = Config.twitter.consumerKey;
            String consumerSecret = Config.twitter.consumerSecret;
            String accessToken = Config.twitter.accessToken;
            String accessTokenSecret = Config.twitter.accessTokenSecret;

            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret);
            builder.setOAuthAccessToken(accessToken);

            builder.setOAuthAccessTokenSecret(accessTokenSecret);
            twitter4j.Twitter twitter = new TwitterFactory(builder.build()).getInstance();

            bind(twitter4j.Twitter.class).toInstance(twitter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
