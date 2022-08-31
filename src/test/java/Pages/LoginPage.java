package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMetods;

public class LoginPage extends MainPage{

    private WebDriver driver=Driver.getDriver();


    By epostaAdressInputBox= By.cssSelector( "[id=\"email\"]");

    By passwordInputBox=By.cssSelector ("[id=\"password\"]");

    By loginButton=By.cssSelector ("[id=\"loginButton\"]");

    By headerMyAccountButton=By.cssSelector("[title=\"Hesabım\"]");

    By headerMyAccountButtonUnderAccount=By.cssSelector("[title=\"Bilgi Güncelleme\"]");

    By emailKnowledgeBox=By.cssSelector("[id=\"emailDisabled\"]");

    public void input_username(){
        driver.findElement(epostaAdressInputBox).sendKeys(ConfigReader.getProperty("email"));
        }

        public void input_password(){
        driver.findElement(passwordInputBox).sendKeys(ConfigReader.getProperty("password"));
        }

        public void press_loginButton(){
        driver.findElement(loginButton).click();
        }

        public void headerAccountButton_hover(){
            ReusableMetods.hover(driver.findElement(headerMyAccountButton));
        }
        public void headerAccoundButtonUnderMyaccoun_click(){
        ReusableMetods.waitForVisibility(headerMyAccountButtonUnderAccount,3);
        driver.findElement(headerMyAccountButtonUnderAccount).click();

        }

        public void userVerify_check(){
        ReusableMetods.waitForPageToLoad(3);
        String expected=ConfigReader.getProperty("email");
        String actual=driver.findElement(emailKnowledgeBox).getAttribute("value");
            Assert.assertEquals(actual,expected);
        }







}
