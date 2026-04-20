import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/*
Checkboxes - проверить, что первый чекбокс unchecked, отметить
первый чекбокс, проверить что он checked. Проверить, что второй чекбокс
checked, сделать unheck, проверить, что он unchecked
 */

public class CheckBoxTest {
    @Test
    public void checkCheckboxes() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        boolean isCheck = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        Assert.assertFalse(isCheck);
        driver.findElements(By.cssSelector("[type=checkbox]")).get(0).click();
        boolean isCheck2 = driver.findElements(By.cssSelector("[type=checkbox]")).get(0).isSelected();
        Assert.assertTrue(isCheck2);

        boolean isCheck3 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        Assert.assertTrue(isCheck3);
        driver.findElements(By.cssSelector("[type=checkbox]")).get(1).click();
        boolean isCheck4 = driver.findElements(By.cssSelector("[type=checkbox]")).get(1).isSelected();
        Assert.assertFalse(isCheck4);

        driver.quit();
    }
}
