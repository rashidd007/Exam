package ua.com.qatestlab.prestashopAautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class DriverLogger implements WebDriverEventListener {
PrintStream writer;

    public DriverLogger() {
        try {
            try {
                PrintWriter wr = new PrintWriter("DriverLog.txt", "UTF-8");
                PrintStream ps  = new PrintStream("DriverLog.txt");
                writer = ps;
                ps.println(LocalDateTime.now());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    public void beforeAlertAccept(WebDriver webDriver) {
        writer.println(" 1");


    }

    public void afterAlertAccept(WebDriver webDriver) {
        System.out.println("2");

    }

    public void afterAlertDismiss(WebDriver webDriver) {
        System.out.println("3");
    }

    public void beforeAlertDismiss(WebDriver webDriver) {
        System.out.println("4");
    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {
        writer.println("Get page "+s);
    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
        System.out.println("6");
    }

    public void beforeNavigateBack(WebDriver webDriver) {
        System.out.println("7");
    }

    public void afterNavigateBack(WebDriver webDriver) {
        System.out.println("8");
    }

    public void beforeNavigateForward(WebDriver webDriver) {
        System.out.println("9");
    }

    public void afterNavigateForward(WebDriver webDriver) {
        System.out.println("10");
    }

    public void beforeNavigateRefresh(WebDriver webDriver) {
        System.out.println("11");
    }

    public void afterNavigateRefresh(WebDriver webDriver) {
        System.out.println("12");
    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        writer.println("FindBy  "+by+ webElement);
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {

    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {

    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void beforeScript(String s, WebDriver webDriver) {

    }

    public void afterScript(String s, WebDriver webDriver) {

    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        writer.println(webElement +": Get text: "+webElement.getText());

    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }
}
