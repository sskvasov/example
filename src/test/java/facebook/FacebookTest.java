package facebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class FacebookTest {
    WebDriver driver;

    @Before
    public void setupClass() {
        System.setProperty("webdriver.ie.driver", "src/test/java/webdriver/IEDriverServer.exe");
//        System.setProperty("webdriver.chrome.driver", "src/test/java/webdriver/chromedriver.exe");
        driver = new InternetExplorerDriver();
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void facebookTest() {
        ObjectMapper mapper = new ObjectMapper();
        DataJson obj = null;
        try {
            obj = mapper.readValue(new File("src/test/java/resources/data.json"), DataJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            driver.navigate().to("https://ru-ru.facebook.com/");
        } catch (Exception e) {
            driver.navigate().to("https://facebook.com/");
        }
        driver.findElement(By.id("email")).sendKeys(obj.username);
        driver.findElement(By.id("pass")).sendKeys(obj.password + Keys.ENTER);
        driver.findElement(By.xpath("//*[text()='Sorry, something went wrong.']"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
