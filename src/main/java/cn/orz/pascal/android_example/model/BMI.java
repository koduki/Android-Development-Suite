package cn.orz.pascal.android_example.model;

public final class BMI {
	private BMI() {
	};

	public static double calc(double weight, double height) {
		return weight / Math.pow(height / 100.0, 2);
	}
}
