import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

    private WebDriver webDriver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

        //get working directory
        String workingDir = System.getProperty("user.dir");

        // Get file
        File file = new File(workingDir + File.separator + "src/main/StyledPage.html");
        String path = "file://" + file.getAbsolutePath();

        
        // Create a new ChromeDriver instance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        // Open the HTML file
        webDriver.get(path);
    }

    @Test
    public void todo1_h1ElementsShouldBeBlue() {
        List<WebElement> elements = webDriver.findElements(By.cssSelector("h1"));
        for(WebElement element: elements) {
            assertEquals("rgba(0, 0, 255, 1)", element.getCssValue("color"));
        }
    }

    @Test
    public void todo2_highlightClassElementsShouldHaveYellowBackground() throws IOException {
        WebElement p2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("highlight")));
        assertEquals("rgba(255, 255, 0, 1)", p2.getCssValue("background-color"));
    }
    
    @Test
    public void todo3_mainTitleShouldBeUppercase() {
        WebElement mainTitle = webDriver.findElement(By.id("main-title"));
        assertEquals("uppercase", mainTitle.getCssValue("text-transform"));
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        webDriver.quit();
    }
}
