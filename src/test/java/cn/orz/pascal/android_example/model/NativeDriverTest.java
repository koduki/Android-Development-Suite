package cn.orz.pascal.android_example.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.android.AndroidDriver;

/**
 * Created with IntelliJ IDEA.
 * User: koduki
 * Date: 12/10/28
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
public class NativeDriverTest {
    public static void main(String[] args){
        WebDriver driver = new AndroidDriver();
        driver.get("http://www.google.co.jp");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("selenium");
        element.submit();
        driver.quit();
    }
}
