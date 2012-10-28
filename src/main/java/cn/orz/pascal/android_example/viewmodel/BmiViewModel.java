package cn.orz.pascal.android_example.viewmodel;

import cn.orz.pascal.android_example.model.BMI;
import cn.orz.pascal.android_example.model.Twitter;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import gueei.binding.Command;
import gueei.binding.observables.DoubleObservable;
import gueei.binding.observables.StringObservable;
import android.view.View;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 * BMI Activity View Model.
 *
 * @author koduki
 */
public class BmiViewModel {
    /**
     * body height.
     */
    public final StringObservable height = new StringObservable();
    /**
     * body weight.
     */
    public final StringObservable weight = new StringObservable();
    /**
     * BMI value.
     */
    public final DoubleObservable bmi = new DoubleObservable(0);

    /**
     * calculate BMI.
     */
    public final Command calculate = new Command() {
        @Override
        public void Invoke(View arg0, Object... arg1) {
            double result = BMI.calc(Double.parseDouble(weight.get()), Double.parseDouble(height.get()));
            bmi.set(result);
        }
    };

    /**
     * post twitter.
     */
    public final Command tweet = new Command() {
        @Override
        public void Invoke(View arg0, Object... arg1) {
            Twitter twitter = createTwitter();
            twitter.tweet(String.format("うわっ…私のBMI、高いすぎ(BMI:%f)", bmi.get()));
        }
    };

    private Twitter createTwitter() {
        Injector injector = Guice.createInjector(
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        try {
                            String consumerKey = "Oiu0AuQnAxiR495Lp6VjoA";
                            String consumerSecret = "Opsj3GCW1ge8GmCEKDD0c4EjSuZ5qtVtgzQ0FAT6ubA";
                            String accessToken = "10509102-YyYM7q1hh0HtrXRgfoAO9tSXNkca1XcRJrNaJEOGz";
                            String accessTokenSecret = "ROZJSbebHa3p3x60nUjjpuk0uqz3njaBtdDiZ9W60";

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
                });
        return injector.getInstance(Twitter.class);
    }
}
