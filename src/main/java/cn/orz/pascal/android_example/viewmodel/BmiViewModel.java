package cn.orz.pascal.android_example.viewmodel;

import android.util.Log;
import cn.orz.pascal.android_example.config.Config;
import cn.orz.pascal.android_example.config.Environment;
import cn.orz.pascal.android_example.injector.TwitterInjector;
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
    public final StringObservable bmi = new StringObservable("0");

    /**
     * calculate BMI.
     */
    public final Command calculate = new Command() {
        @Override
        public void Invoke(View arg0, Object... arg1) {
            double result = BMI.calc(Double.parseDouble(weight.get()), Double.parseDouble(height.get()));
            bmi.set(String.format("%.1f", result));
        }
    };

    /**
     * post twitter.
     */
    public final Command tweet = new Command() {
        @Override
        public void Invoke(View arg0, Object... arg1) {
            BMISocialService bmiSocialService = createBmiSocialService();
            Log.d("tweet bmi:", bmi.get());
            bmiSocialService.tweet(Double.parseDouble(bmi.get()));
        }
    };

    private BMISocialService createBmiSocialService() {
        Injector injector = Guice.createInjector(new TwitterInjector(Environment.getInstance().getProfile()));
        return injector.getInstance(BMISocialService.class);
    }
}
