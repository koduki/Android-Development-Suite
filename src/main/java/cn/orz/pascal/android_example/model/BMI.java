package cn.orz.pascal.android_example.model;

/**
 * MBI(Body Mass Index) calculator.
 * 
 * @author koduki
 * 
 */
public final class BMI {
  private BMI() {};

  /**
   * calculate BMI.
   * 
   * @param weight body weight.
   * @param height body height.
   * @return calculate result.
   */
  public static double calc(double weight, double height) {
    return weight / Math.pow(height / 100.0, 2);
  }
}
