package ua.com.qatestlab.prestashopAautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPageObject extends PageObject {

    public MainPageObject(WebDriver driver) {
        super(driver);
    }
    private WebElement selectedCurrencyEl = driver.findElement(By.cssSelector("span.expand-more._gray-darker.hidden-sm-down"));
    private List<WebElement> allPrices = driver.findElements(By.cssSelector("div.product-description > div > span.price"));
    private WebElement currencyDropMenu = driver.findElement(By.cssSelector("#_desktop_currency_selector > div > a > i"));
    private WebElement currencyUSD = driver.findElement(By.cssSelector("#_desktop_currency_selector > div > ul > li:nth-child(3) > a"));
    private WebElement findInput = driver.findElement(By.cssSelector("#search_widget > form > input.ui-autocomplete-input"));


    public String SelectedCurrency(){
        System.out.println("Take selected currency: "+selectedCurrencyEl.getText() );
        return selectedCurrencyEl.getText();
    }

    public List <String> PriceList(){
        System.out.println("Take prices at popular staff page");
        List <String> prices = new ArrayList<String>();
        for (WebElement w: allPrices)
            prices.add(w.getText());
        System.out.print("Prices: "+prices);
        return prices;
    }

    //Установить показ цены в USD используя выпадающий список в шапке сайта
    public void ChangeCurrencyUSD(){
        currencyDropMenu.click();
        this.pause(500);
        currencyUSD.click();
        this.pause(2000);
    }

    //Выполнить поиск в каталоге по слову “dress.”
    public void FindDress(){
        findInput.clear();
        this.pause(500);
        findInput.sendKeys("dress");
        this.pause(1000);
        findInput.submit();
        this.pause(1000);
    }
}
