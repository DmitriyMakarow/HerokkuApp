import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
Typos - Проверить соответствие параграфа орфографии
(10 раз обновить страницу и проверить сколько раз апостроф в слове "won't" превращается в запятую)
 */

public class TyposTest {

    @Test
    public void typos() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/typos");

        for (int i = 0; i < 10; i++) {
            driver.navigate().refresh();
            String text = driver.findElement(By.xpath("(//p[2])")).getText();
            softAssert.assertEquals(text, "Sometimes you'll see a typo, other times you won't.");
            System.out.println(text);
        }
        driver.quit();
        softAssert.assertAll();
    }
}
