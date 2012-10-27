package cn.orz.pascal.slideshare_viewer;

import android.view.View;
import gueei.binding.Command;
import gueei.binding.observables.DoubleObservable;
import gueei.binding.observables.StringObservable;
import gueei.binding.pojo.PojoViewModel;
import gueei.binding.pojo.PojoViewModelHelper;

public class BmiModel implements PojoViewModel {

	private PojoViewModelHelper helper = new PojoViewModelHelper();

	public final StringObservable height = new StringObservable();
	public final StringObservable weight = new StringObservable();
	public final DoubleObservable bmi = new DoubleObservable(0);

	@Override
	public PojoViewModelHelper getHelper() {
		return helper;
	}

	@Override
	public void notifyPropertyChanged(String propertyName) {
		helper.notifyPropertyChanged(propertyName);
	}

	public Command calculate = new Command() {
		@Override
		public void Invoke(View arg0, Object... arg1) {
			double buf = Double.parseDouble(weight.get())
					/ Math.pow(Double.parseDouble(height.get()) / 100.0, 2);
			bmi.set(buf);
		}
	};

}
