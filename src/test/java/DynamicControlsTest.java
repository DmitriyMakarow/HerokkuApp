import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/*
b. Dynamic Controls

- нажать на кнопку Remove около чекбокса
- дождаться надписи “It’s gone”
- проверить, что чекбокса нет
- найти инпут
- проверить, что он disabled
- нажать на кнопку
- дождаться надписи “It's enabled!”
- проверить, что инпут enabled
 */

@Test
public class DynamicControlsTest {
    public void checkDynamicControls() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        driver.findElement(By.xpath("//*[text() = 'Remove']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='message'" +
                " and contains (text() ,'gone!')]")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
        softAssert.assertFalse(driver.findElement(By.cssSelector("#input-example > input[type=text]")).isEnabled());
        driver.findElement(By.xpath("//*[text() = 'Enable']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='message'" +
                " and contains (text() ,'enabled!')]")));
        softAssert.assertTrue(driver.findElement(By.cssSelector("#input-example > input[type=text]")).isEnabled());
        driver.quit();
        softAssert.assertAll();
    }
}
