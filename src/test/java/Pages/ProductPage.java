package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Driver;
import utilities.ReusableMetods;

public class ProductPage extends LoginPage{

    private WebDriver driver=Driver.getDriver();


   By secondPageButon= By.cssSelector("[data-page=\"2\"]");

   By productName=By.cssSelector("[class=\"proName\"]");

   By addBasketButon=By.cssSelector("[class=\"product-add-cart\"]");

   By myBasketButon=By.cssSelector("[title=\"Sepetim\"]");

   public void secondPageButton_click(){
       ReusableMetods.scrollToWebEementVisivle(driver.findElement(secondPageButon));
       ReusableMetods.waitForVisibility(driver.findElement(secondPageButon),3);
       driver.findElement(secondPageButon).click();
   }

   public String get_productName_title(){
       return driver.findElement(productName).getText();
   }
   public void add_basketButton_click(){
       ReusableMetods.waitForClickablility(addBasketButon,3);
       driver.findElement(addBasketButon).click();
   }
   public void goto_myBasketButton_click(){
       driver.findElement(myBasketButon).click();
   }

}
