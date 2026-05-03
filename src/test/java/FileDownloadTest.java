import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class FileDownloadTest {

    @Test
    public void checkFileDownload() {
        // Настройка пути
        String projectDir = System.getProperty("user.dir");
        String downloadPath = projectDir + "\\src\\test\\resources";
        System.out.println("Chrome will download to: " + downloadPath);
        // Настройка опций Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadPath);
        chromePrefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = null;
        try {
            driver = new ChromeDriver(options);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.navigate().to("https://the-internet.herokuapp.com/download");
            // Клик по ссылке (скачивается test-file.txt)
            WebElement link = driver.findElement(By.cssSelector("#content a"));
            link.click();
            // Явное ожидание появления файла на диске
            File downloadedFile = new File(downloadPath + "\\test-file.txt");
            WebDriverWait waitForFile = new WebDriverWait(driver, Duration.ofSeconds(20));
            waitForFile.until((ExpectedCondition<Boolean>) webDriver -> {
                boolean exists = downloadedFile.exists();
                return exists;
            });
            // Проверка
            assertTrue(downloadedFile.exists());
        } finally {
            // Завершение работы
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
