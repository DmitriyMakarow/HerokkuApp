import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

/*
- Открыть iFrame
- Проверить, что текст внутри параграфа равен “Your content
goes here.”
 */

public class FramesTest {

    @Test
    public void checkFrames() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame(0);
        String text = driver.findElement(By.cssSelector("#tinymce > p")).getText();
        assertEquals(text, "Your content goes here.");
        driver.quit();
    }
}
