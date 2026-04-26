import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

/*
Dropdown - Взять все элементы дроп-дауна и проверить их наличие.
Выбрать первый, проверить, что он выбран, выбрать второй, проверить, что
он выбран
 */

public class DropDownTest {
    @Test
    public void checkDropdown() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notification");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);

        List<WebElement> options1 = dropdown.getOptions();
        softAssert.assertEquals(options1.size(), 3);
        softAssert.assertEquals(options1.get(0).getText(), "Please select an option");
        softAssert.assertEquals(options1.get(1).getText(), "Option 1");
        softAssert.assertEquals(options1.get(2).getText(), "Option 2");

        dropdown.selectByVisibleText("Option 1");
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");

        dropdown.selectByVisibleText("Option 2");
        softAssert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2");

        driver.quit();
    }
}
