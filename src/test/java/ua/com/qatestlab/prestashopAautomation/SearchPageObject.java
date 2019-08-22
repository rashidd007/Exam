package ua.com.qatestlab.prestashopAautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageObject extends PageObject{
    public SearchPageObject(WebDriver driver){
        super(driver);
    }

    private WebElement sortDropMenu = driver.findElement(By.cssSelector("#js-product-list-top > div:nth-child(2) > div > div > a > i"));
    private WebElement sortPriceDown = driver.findElement(By.cssSelector("#js-product-list-top > div:nth-child(2) > div > div > div > a:nth-child(5)"));
    private WebElement findResultCountEl = driver.findElement(By.cssSelector("#js-product-list-top > div.col-md-6.hidden-sm-down.total-products > p"));
    private List<WebElement> allFindPrices = driver.findElements(By.cssSelector("div.product-description > div > span.price"));
    private List<WebElement> allFindPricesDiscount = driver.findElements(By.cssSelector("div.product-description > div > span"));
    private WebElement selectedCurrencyEl = driver.findElement(By.cssSelector("span.expand-more._gray-darker.hidden-sm-down"));

//String Список цен форматирует и возвращает Float список
    public List<Float> unformatStringToFloat(List <String> st){
        List <Float> fl = new ArrayList<Float>();
        for(int i=0; i<st.size(); i++){
            String buf = "";
            for(int j=0; j<st.get(i).length(); j++){
                if(st.get(i).charAt(j)!='-')
                    if(st.get(i).charAt(j)!='%')
                        if(st.get(i).charAt(j)!=' ')
                            if(st.get(i).charAt(j)!=',')
                                buf+= st.get(i).charAt(j);
                            else buf+='.';
                        else break;
                    else break;
            }
            fl.add(Float.valueOf(buf));
        }
        return fl;
    }
    //список скидки для каждой цены
    public List <String> DiscountList(){
        List <String> discount = new ArrayList<String>();
        for (int i=0; i<allFindPricesDiscount.size(); i++) {
            if (allFindPricesDiscount.get(i).getText().length() < 6){
                if(discount.size()>0)
                    discount.remove(discount.size()-1);
                discount.add(allFindPricesDiscount.get(i).getText());
                i++;
            }
            else
                discount.add("0");
        }
        return discount;


    }

    //возвращает выбраную валюту
    public String SelectedCurrency(){
        return selectedCurrencyEl.getText();

    }

    //список цен с учетом скидки
    public List<String> ResultPriceList(){
        List <String> prices = new ArrayList<String>();
        for (WebElement w: allFindPrices)
            prices.add(w.getText());
        return prices;
    }

    //список цен без учета скидки
    public List<String> ResultPricesListWithoutDiscount(){
        List <String> prices = new ArrayList<String>();
        for (int i=0; i<allFindPricesDiscount.size(); i++) {
            if (allFindPricesDiscount.get(i).getText().length() > 5){
                prices.add(allFindPricesDiscount.get(i).getText());
            }
            else {
                i++;
            }
        }
        return prices;
    }

    //количиство(которое котоое посчитал сайт) товаров поиска числом
    public  int findResultCount(){
        String findCount ="";
        for(int i=9;i<findResultCountEl.getText().length()-1; i++ )
            findCount += findResultCountEl.getText().charAt(i);
        int intCount = Integer.valueOf(findCount);
        return intCount;
    }

    //действие: сортировка по спаданию цены
    public void SortByPriceDown(){
        sortDropMenu.click();
        this.pause(500);
        sortPriceDown.click();
        this.pause(2000);
    }
}
