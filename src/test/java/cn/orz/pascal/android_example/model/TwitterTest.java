package cn.orz.pascal.android_example.model;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import static org.mockito.Mockito.*;

public class TwitterTest extends TestCase {

    public void test1(){
        final String message = "Hello World.";
        final String name = "koduki";

        Twitter twitter = createTwitter(message, name);
        Status result = twitter.tweet(message);

        assertEquals(name, result.getUser().getScreenName());
        assertEquals(message, result.getText());
    }

    private Twitter createTwitter(final String message, final String name) {
        Injector injector = Guice.createInjector(
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        try {
                            twitter4j.Twitter twitter = mock(twitter4j.Twitter.class);
                            Status status = mock(Status.class);
                            User user = mock(User.class);

                            when(twitter.updateStatus(message)).thenReturn(status);
                            when(status.getText()).thenReturn(message);
                            when(status.getUser()).thenReturn(user);
                            when(user.getScreenName()).thenReturn(name);

                            bind(twitter4j.Twitter.class).toInstance(twitter);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        return injector.getInstance(Twitter.class);
    }
}
