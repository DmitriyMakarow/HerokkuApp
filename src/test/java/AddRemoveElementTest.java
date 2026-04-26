import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

/*
1. Add/Remove Elements - добавить 2 элемента, удалить элемент,
проверить количество элементов DELETE
 */

public class AddRemoveElementTest {

    @Test
    public void checkAddRemoveElement() {
        // задаем опции для нашего драйвера
        ChromeOptions options = new ChromeOptions();
        // открывает окно браузера на весь экран
        options.addArguments("--start-maximized");
        // открывает вкладку браузера в режиме инкогнито
        options.addArguments("--incognito");
        // отключает нотификации на сайте
        options.addArguments("--disable-notification");
        // определяем браузер, с которым хотим работать
        WebDriver driver = new ChromeDriver(options);
        // неявное ожидание, в течении 10 секунд ожидаем, что при открытии страницы, появится кнопка "Add Element"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // создание объекта класса SoftAssert
        SoftAssert softAssert = new SoftAssert();
        //открывает страницу по указанному url
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        //дважды нажимает на кнопку "Add Element"
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        driver.findElement(By.xpath("//button[text()='Add Element']")).click();

        // проверка, что на странице отображается 2 элемента с текстом "Delete"
        int size = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(size, 2);

        // один раз нажимает на кнопку "Delete"
        driver.findElement(By.xpath("//button[text()='Delete']")).click();

        // проверка, что на странице отображается 1 элемент с текстом "Delete"
        int size1 = driver.findElements(By.xpath("//button[text()='Delete']")).size();
        softAssert.assertEquals(size1, 1);

        // закрывает браузер
        driver.quit();
    }
}
