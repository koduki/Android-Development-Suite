package cn.orz.pascal.slideshare_viewer;

import gueei.binding.app.BindingActivity;
import android.os.Bundle;

public class MainActivity extends BindingActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		// モデルを生成
		BmiModel model = new BmiModel();

		// Layout.xmlの binding:xxx と modelを関連付ける
		setAndBindRootView(R.layout.main, model);
	}
}