package cn.orz.pascal.android_example.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BMITest {
    private static final double DELTA = 1e-1;

    @Test
    public void testRegularCase1() {
        assertEquals(19.5, BMI.calc(50, 160), DELTA);
    }

    @Test
    public void testRegularCase2() {
        assertEquals(24.3, BMI.calc(72, 172), DELTA);
    }
}
