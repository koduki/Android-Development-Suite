package cn.orz.pascal.android_example.model;

import com.google.inject.Inject;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Twitter Client.
 *
 * @author koduki
 */
public class BMISocialService {
    @Inject
    private twitter4j.Twitter twitter;

    public BMISocialService(){}

    public Status tweet(double bmi) {
        try {
            return this.twitter.updateStatus(getMessage(bmi));
        } catch (TwitterException e) {
            throw new RuntimeException("fail tweet.", e);
        }
    }

    public String getMessage(double bmi) {
        String message = (bmi < BMI.AVERAGE) ?
                String.format("うわっ…私のBMI、低すぎ(BMI:%.1f)", bmi)
                :String.format("うわっ…私のBMI、高すぎ(BMI:%.1f)", bmi);
        return message;
    }
}