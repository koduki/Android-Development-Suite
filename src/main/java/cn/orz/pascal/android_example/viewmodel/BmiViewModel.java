package cn.orz.pascal.android_example.viewmodel;

import cn.orz.pascal.android_example.config.Config;
import cn.orz.pascal.android_example.model.BMI;
import cn.orz.pascal.android_example.model.BMISocialService;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import gueei.binding.Command;
import gueei.binding.observables.DoubleObservable;
import gueei.binding.observables.StringObservable;
import android.view.View;
import twitter4j.TwitterFactory;
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
            BMISocialService bmiSocialService = createBmiSocialService();
            bmiSocialService.tweet(bmi.get());
        }
    };

    private BMISocialService createBmiSocialService() {
        Injector injector = Guice.createInjector(
                new AbstractModule() {
                    @Override
                    protected void configure() {
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
                });
        return injector.getInstance(BMISocialService.class);
    }
}
