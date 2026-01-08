import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class SeleniumTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");

        // Get file
        File file = new File("StyledPage.html");
        String path = "file://" + file.getAbsolutePath();

        
        EdgeOptions options = new EdgeOptions();
        options.addArguments("headless");
        webDriver = new EdgeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }

    

    @Test
    public void testIdSelector() {
        WebElement p2 = webDriver.findElement(By.id("p2"));
        assertEquals("rgba(255, 0, 0, 1)", p2.getCssValue("color"));
    }

    @Test
    public void testClassSelector() {
        List<WebElement> elements = webDriver.findElements(By.className("class2"));
        for(WebElement element: elements) {
            assertEquals("rgba(0, 0, 255, 1)", element.getCssValue("color"));
        }
    }

    @Test
    public void testElementSelector() {
        List<WebElement> elements = webDriver.findElements(By.cssSelector("h2"));
        for(WebElement element: elements) {
            assertEquals("rgba(0, 255, 255, 1)", element.getCssValue("color"));
        }
    }

    @After
    public void tearDown() {
        // Close the browser
        webDriver.quit();
    }
}
