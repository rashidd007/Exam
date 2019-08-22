package ua.com.qatestlab.prestashopAautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainTest {
    public static void main(String arg[]){
        System.setProperty("webdriver.chrome.driver", "/Java/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        EventFiringWebDriver driverEvent = new EventFiringWebDriver(driver);
        DriverLogger activity = new DriverLogger();
        driverEvent.register(activity);
        Tests ts = new Tests(driverEvent);
        driverEvent.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverEvent.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        System.out.println("Step 1: Open main page");
        driverEvent.get("http://prestashop-automation.qatestlab.com.ua/ru/");

        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MainPageObject mp = new MainPageObject(driverEvent);

        System.out.println("Step 2: Test compare currency in section popular");

        ts.test2CurrencyEqual();

        System.out.println("Step 3: Change currency on USD");
        mp.ChangeCurrencyUSD();

        MainPageObject mp2 = new MainPageObject(driverEvent);

        System.out.println("Step 4: Find 'dress' on portal ");
        mp2.FindDress();

        SearchPageObject sp = new SearchPageObject(driverEvent);

        System.out.println("Step 5: Compare number search result");
        ts.test3CompareSearchResult();

        System.out.println("Step 6: Check all price in USD ");
        ts.test4CurrencyEqual();

        System.out.println("Step 7: Sort goods by less prise");
        sp.SortByPriceDown();

        System.out.println("Step 8:Check that the goods are sorted by price");
        ts.test5CheckSort();

        System.out.println("Step 9:Check if discount are correct ");
        ts.test6CheckDiscount();

        driverEvent.manage().deleteAllCookies();
        driverEvent.close();




    }
}
