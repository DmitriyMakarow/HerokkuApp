import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
Inputs - Проверить на возможность ввести различные цифровые и
нецифровые значения, используя Keys.ARROW_UP И
Keys.ARROW_DOWN
 */

public class InputsTest {
    @Test
    public void checkInputs() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/inputs");

        // попытка ввести текст в поле
        driver.findElement(By.tagName("input")).sendKeys("test");
        String text = driver.findElement(By.tagName("input")).getAttribute("Value");
        // проверка, что в поле отсутствует введенный текст
        softAssert.assertEquals(text, "test");

        // очистка инпута, т.к. возможно ввести букву "e"
        WebElement element = driver.findElement(By.tagName("input"));
        element.clear();

        //ввод валидного значения
        driver.findElement(By.tagName("input")).sendKeys("10");
        String value1 = driver.findElement(By.tagName("input")).getAttribute("value");
        // проверка, что в поле есть введенное значение
        softAssert.assertEquals(value1, "10");

        // увеличение значения с помощью степпера
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        String value2 = driver.findElement(By.tagName("input")).getAttribute("value");
        // проверка, что значение увеличилось
        softAssert.assertEquals(value2, "11");

        // уменьшение значения с помощью степпера
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        String value3 = driver.findElement(By.tagName("input")).getAttribute("value");
        // проверка, что значение уменьшилось
        softAssert.assertEquals(value3, "10");

        driver.quit();
        softAssert.assertAll();
    }
}
