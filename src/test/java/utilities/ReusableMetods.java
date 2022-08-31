package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ReusableMetods {

  public static void hover(WebElement webElement) {
    Actions actions = new Actions(Driver.getDriver());
    actions.moveToElement(webElement).perform();
  }

  public static void waitfor(int second) {
      try {
          Thread.sleep(1000 * second);
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (TimeoutException e) {
          e.printStackTrace();
      } catch (NoSuchElementException e) {
          e.printStackTrace();
      } catch (StaleElementReferenceException e) {
          e.printStackTrace();
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  public static String tumSayfaScreenShot(String sayfaAdi) {

    TakesScreenshot tss = (TakesScreenshot) Driver.getDriver();
    File sayfaninResmi = new File("target/screenShot/" + sayfaAdi + ".jpg");
    File geciciSs = tss.getScreenshotAs(OutputType.FILE);
      String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + sayfaAdi+ ".png";
      File finalDestination = new File(target);
    try {
      FileUtils.copyFile(geciciSs, sayfaninResmi);
    } catch (IOException e) {
      e.printStackTrace();
    }
      return target;
  }

  public static void scrollToWebEementVisivle(WebElement webElement){
      JavascriptExecutor jss=(JavascriptExecutor) Driver.getDriver();
      jss.executeScript("arguments[0].scrollIntoView(true);",webElement);
  }

  public static List<String> getElementList(By locator) {
    List<WebElement> elemans = Driver.getDriver().findElements(locator);
    List<String> elemanString = new ArrayList<>();

    for (WebElement each : elemans) {
      if (!each.getText().isEmpty()) {
        elemanString.add(each.getText());
      }
    }
    return elemanString;
  }


    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }



}
