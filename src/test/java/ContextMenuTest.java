import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.time.Duration;

/*
a. Context Menu

- правый клик по элементу
- валидация текста на алерте
- закрытие алерта
 */

import static org.testng.Assert.assertEquals;

public class ContextMenuTest {

    @Test
    public void checkContextMenu() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        // создание объекта Actions
        Actions actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/context_menu");
        // клик правой кнопкой мыши по элементу
        actions.contextClick(driver.findElement(By.id("hot-spot"))).perform();
        // переключение на alert
        Alert alert = driver.switchTo().alert();
        // инициализируем переменную для получения текста в алерте
        String text = alert.getText();
        // сравнение полученного текста
        assertEquals(text, "You selected a context menu");
        // нажатие "ок" в алерте
        alert.accept();
        driver.quit();
    }
}
