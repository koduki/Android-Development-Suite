package cn.orz.pascal.android_example.viewmodel;

import cn.orz.pascal.android_example.model.BMI;
import gueei.binding.Command;
import gueei.binding.observables.DoubleObservable;
import gueei.binding.observables.StringObservable;
import android.view.View;

public class BmiViewModel {
	public final StringObservable height = new StringObservable();
	public final StringObservable weight = new StringObservable();
	public final DoubleObservable bmi = new DoubleObservable(0);

	public final Command calculate = new Command() {
		@Override
		public void Invoke(View arg0, Object... arg1) {
			double buf = BMI.calc(Double.parseDouble(weight.get()), Double.parseDouble(height.get()));
			bmi.set(buf);
		}
	};

}
