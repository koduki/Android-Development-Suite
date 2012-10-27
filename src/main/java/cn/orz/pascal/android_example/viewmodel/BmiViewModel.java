package cn.orz.pascal.android_example.viewmodel;

import cn.orz.pascal.android_example.model.BMI;
import gueei.binding.Command;
import gueei.binding.observables.DoubleObservable;
import gueei.binding.observables.StringObservable;
import android.view.View;

/**
 * BMI Activity View Model.
 * 
 * @author koduki
 * 
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

}
