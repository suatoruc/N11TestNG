package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilities.Driver;
import utilities.TestBaseRapor;

public class MainPage extends TestBaseRapor {

    private WebDriver driver=Driver.getDriver();

   By headerSinginButon= By.cssSelector("[class=\"btnSignIn\"]");

    By headerSearchInputBox=By.cssSelector("[id=\"searchData\"]");

    public void sigupButton_click(){
        driver.findElement(headerSinginButon).click();
    }
    public void headerSearch_item(String searchItem){
        driver.findElement(headerSearchInputBox).sendKeys(searchItem+ Keys.ENTER);
    }


}
