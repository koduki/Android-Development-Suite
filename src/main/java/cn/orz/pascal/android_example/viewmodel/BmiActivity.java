package cn.orz.pascal.android_example.viewmodel;

import cn.orz.pascal.android_example.R;
import gueei.binding.app.BindingActivity;
import android.os.Bundle;

public class BmiActivity extends BindingActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		// モデルを生成
		BmiViewModel model = new BmiViewModel();

		// Layout.xmlの binding:xxx と modelを関連付ける
		setAndBindRootView(R.layout.main, model);
	}
}