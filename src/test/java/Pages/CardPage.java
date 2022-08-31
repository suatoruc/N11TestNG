package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class CardPage extends ProductPage {
    private WebDriver driver = Driver.getDriver();

    By bProductDescriptionText = By.cssSelector("[class=\"prodDescription\"]");

    By productEraseButon = By.cssSelector("[title=\"Sil\"]");

    By popupDeleteButon = By.cssSelector("[id=\"deleteBtnDFLB\"]");

    By emptyBasketText = By.xpath("(//h2[@class=\"title\"])[1]");

    public String basketProduct_title() {
        return driver.findElement(bProductDescriptionText).getText();

    }

    public void productEraseButton_click() {
        driver.findElement(productEraseButon).click();
    }

    public void popupDeletButton_click() {
        driver.findElement(popupDeleteButon).click();
    }

    public String emptyBasket_text() {
        return driver.findElement(emptyBasketText).getText();
    }


}
