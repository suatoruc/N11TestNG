package tests;


import Pages.CardPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMetods;

import java.util.List;
import java.util.Random;

public class N11_UITest extends CardPage {

    @Test
    public void login(){
        extentTest=extentReports.createTest("N11 TestNG Report ","N11 Login Olma");
        Driver.getDriver().get(ConfigReader.getProperty("mainPage"));

        extentTest.info("Kullanıcı Sigup Butonuna Tiklar");
        ReusableMetods.waitForPageToLoad(5);
        sigupButton_click();

        extentTest.info("Kullanici Adi ve Password Bilgisi Girilerek Login Butonuna Basilir");
        ReusableMetods.waitForPageToLoad(5);
        input_username();
        input_password();
        press_loginButton();

        extentTest.info("Kullanici Güncelleme Sayfasina Giderek Email Bilgisini Doğrular");
        headerAccountButton_hover();
        headerAccoundButtonUnderMyaccoun_click();
        ReusableMetods.waitForPageToLoad(5);
        userVerify_check();

    }

    @Test(dependsOnMethods = "login")
    public void searchProduct(){
        extentTest=extentReports.createTest("N11 TestNG Report ","N11 Islemler");
        extentTest.info("Arama Cubugunda Urun Aramasi Yapilir");
        headerSearch_item("Telefon");

        extentTest.info("Arama Sonrası Gelen Urun Listesinde Ikinci Sayfaya Gidilir");
        ReusableMetods.waitForPageToLoad(3);
        secondPageButton_click();

        extentTest.info("Ikinci Sayfadan Rastgele bir Urun Secilir");
        rastgeleUrunSec();

        extentTest.info("Secilen Urunun Title Bilgisi Dogrulama Icin bir Degiskene Assign Edilir");
        ReusableMetods.waitfor(3);
        String productTitle=get_productName_title();

        extentTest.info("Secilen Urun Sepete eklenir");
        add_basketButton_click();

        extentTest.info("Sepet Sayfasina Gidilir.");
        goto_myBasketButton_click();

        extentTest.info("Sepet Sayfasinda Gorülen Urunun Title Bilgisi Alınarak, Dogrulama Yapılı");
        ReusableMetods.waitfor(5);
        String basketProductTitle=basketProduct_title();
        Assert.assertEquals(basketProductTitle,productTitle);

        extentTest.info("Urun Sepetten Silinir");
        productEraseButton_click();
        popupDeletButton_click();

        extentTest.info("Sepetin Boş Oldugu Dogrulanır");
        String expectedEmptyTitle="Sepetin Boş Görünüyor";
        String actualEmptyBasketTitle=emptyBasket_text();
        Assert.assertEquals(actualEmptyBasketTitle,expectedEmptyTitle);

}

    private void rastgeleUrunSec() {
        List<String>urunListesi=ReusableMetods.getElementList(By.cssSelector("[class=\"pro\"]"));
        Random random=new Random();
          int secilenUrunSirasi=random.nextInt(0,urunListesi.size());
          String dinamikPath="(//div[@class=\"pro\"])["+secilenUrunSirasi+"]";
          ReusableMetods.scrollToWebEementVisivle( Driver.getDriver().findElement(By.xpath(dinamikPath)));
          Driver.getDriver().findElement(By.xpath(dinamikPath)).click();
    }


}
