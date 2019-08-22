package ua.com.qatestlab.prestashopAautomation;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;


public class Tests {
    protected WebDriver driver;

    public Tests(WebDriver driver){
        this.driver = driver;
    }


        // Открыть главную страницу сайта
        @Test
                public void test1MainPage(){

            driver.get("http://prestashop-automation.qatestlab.com.ua/ru/");
            System.out.println("Step1: Open main page");
            String title;
            PageObject po = new PageObject(driver);
            po.pause(1000);
            title = driver.getTitle();
            Assert.assertTrue(title.equals("prestashop-automation"));
            System.out.println("Test 1 completed successfully");
        }

        //Выполнить проверку, что цена продуктов в секции "Популярные товары"
        // указана в соответствии с установленной валютой в шапке сайта (USD, EUR, UAH).
        @Test
                public void test2CurrencyEqual() {
             System.out.println("Tes started. Compare currency");
             MainPageObject mp = new MainPageObject(driver);
             String selectedCurrency = mp.SelectedCurrency();
             List<String> prices = mp.PriceList();
             int falsecounts = 0;
             for (String s : prices) {
                 if ((selectedCurrency.charAt(selectedCurrency.length() - 1)) != (s.charAt(s.length() - 1)))
                     falsecounts++;
             }
             Assert.assertEquals(0, falsecounts);
             System.out.println("Test compare currency - completed successfully");

        }

        //Выполнить проверку, что страница "Результаты поиска" содержит надпись "Товаров: x",
        // где x - количество действительно найденных товаров.

        @Test
        public void test3CompareSearchResult () {
            System.out.println("Test started. Compare search result");
            SearchPageObject sp = new SearchPageObject(driver);
            sp.pause(1000);
            Assert.assertEquals(sp.findResultCount(), sp.ResultPriceList().size());
            System.out.println("Test Compare search result - completed successfully");
        }

        //Проверить, что цена всех показанных результатов отображается в долларах
        @Test
                public void test4CurrencyEqual(){
            System.out.println("Test started. Compare currency ");
            SearchPageObject sp = new SearchPageObject(driver);
            sp.pause(1000);
            String selectedCurrency = sp.SelectedCurrency();
            List<String> prices = sp.ResultPriceList();
            int falsecounts = 0;
            for (String s : prices) {
                if ((selectedCurrency.charAt(selectedCurrency.length() - 1)) != (s.charAt(s.length() - 1)))
                    falsecounts++;
            }
            Assert.assertEquals(0, falsecounts);
            System.out.println("Test Compare currency - completed successfully");
        }

        //Проверить, что товары отсортированы по цене, при этом некоторые товары могут быть со скидкой,
        // и при сортировке используется цена без скидки.
        @Test
                public void test5CheckSort(){
            System.out.println("Test started. Check sort price down");
            SearchPageObject sp = new SearchPageObject(driver);
            sp.pause(2000);
            List <Float> sortPrice = sp.unformatStringToFloat(sp.ResultPricesListWithoutDiscount());
            List <Float> unsortPrice = sp.unformatStringToFloat(sp.ResultPricesListWithoutDiscount());
            Collections.sort(sortPrice, Collections.<Float>reverseOrder());
            System.out.println("unsort price: "+unsortPrice);
            System.out.println("Sort price: "+sortPrice);
            Assert.assertEquals(sortPrice, unsortPrice);
            System.out.println("Test check sort price down - completed successfully");


        }

        //Необходимо проверить, что цена до и после скидки совпадает с указанным размером скидки
        @Test
                public void test6CheckDiscount() {
            System.out.println("Test started. Check if discount correct");
            SearchPageObject sp = new SearchPageObject(driver);
            sp.pause(2000);
            List<Float> discountList = sp.unformatStringToFloat(sp.DiscountList());
            List<Float> discountPriceList = sp.unformatStringToFloat(sp.ResultPriceList());
            List<Float> fullPriceList = sp.unformatStringToFloat(sp.ResultPricesListWithoutDiscount());
            List<Float> myDiscList = new ArrayList<Float>();
            System.out.println("Discounts: " + discountList);
            float res1,res2;
            for(int i=0; i<discountList.size(); i++){
                res1 = (fullPriceList.get(i)-((discountList.get(i)*fullPriceList.get(i))/100))*100;
                res2 = Math.round(res1);
                myDiscList.add(res2/100);
            }
            System.out.println("Result  : "+myDiscList);
            System.out.println("Original: "+discountPriceList);
            Assert.assertEquals(myDiscList, discountPriceList);
            System.out.println("Test check if discount correct completed successfully");
        }

}


