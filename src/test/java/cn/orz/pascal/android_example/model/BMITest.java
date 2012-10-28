package cn.orz.pascal.android_example.model;

import junit.framework.TestCase;

public class BMITest extends TestCase {
    private static final double DELTA = 1e-1;

    public void testRegularCase1() {
        assertEquals(19.5, BMI.calc(50, 160), DELTA);
    }

    public void testRegularCase2() {
        assertEquals(24.3, BMI.calc(72, 172), DELTA);
    }
}
