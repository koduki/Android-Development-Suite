package cn.orz.pascal.android_example.model;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import twitter4j.Status;
import twitter4j.User;

import static org.mockito.Mockito.*;

public class BMISocialServiceTest extends TestCase {

    public void testTweet1(){
        String name = "koduki";

        BMISocialService bmiSocialService = createInstance(name);
        Status result = bmiSocialService.tweet(24.3374);

        assertEquals(name, result.getUser().getScreenName());
        assertEquals("うわっ…私のBMI、高すぎ(BMI:24.3)", result.getText());
    }

    public void testTweet2(){
        String name = "koduki";

        BMISocialService bmiSocialService = createInstance(name);
        Status result = bmiSocialService.tweet(19.5074);

        assertEquals(name, result.getUser().getScreenName());
        assertEquals("うわっ…私のBMI、低すぎ(BMI:19.5)", result.getText());
    }

    private BMISocialService createInstance(final String name) {
        Injector injector = Guice.createInjector(
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        try {
                            twitter4j.Twitter twitter = mock(twitter4j.Twitter.class);
                            when(twitter.updateStatus(anyString())).thenAnswer(new Answer() {
                                @Override
                                public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                                    Object[] args = invocationOnMock.getArguments();

                                    Status status = mock(Status.class);
                                    User user = mock(User.class);
                                    when(status.getText()).thenReturn((String)args[0]);
                                    when(status.getUser()).thenReturn(user);
                                    when(user.getScreenName()).thenReturn(name);
                                    return status;
                                }
                            });
                            bind(twitter4j.Twitter.class).toInstance(twitter);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        return injector.getInstance(BMISocialService.class);
    }
}
