package scripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class demo3 {
    static WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void navigateToHomePage() {
        driver.get("https://tiki.vn");
        closePopupIfPresent();
    }

    @Test
    public void searchAdidas() {
        searchKeyword("adidas");
        boolean found = verifyAtLeastOneItemContainsKeyword("adidas");
        Assert.assertTrue(found, "❌ Không có item nào trong 8 item đầu tiên chứa chữ 'adidas'");
    }

    @Test
    public void searchSamsung() {
        searchKeyword("samsung");
        boolean found = verifyAtLeastOneItemContainsKeyword("samsung");
        Assert.assertTrue(found, "❌ Không có item nào trong 8 item đầu tiên chứa chữ 'samsung'");
    }

    // --- Hàm tái sử dụng ---

    private void searchKeyword(String keyword) {
        try {
            WebElement searchClick = driver.findElement(By.xpath("//div[@class='sc-dec0a11d-1 cfhkdd']"));
            searchClick.click();
        } catch (Exception e) {
            System.out.println("Không cần click ô tìm kiếm.");
        }

        WebElement searchBox = driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']"));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private boolean verifyAtLeastOneItemContainsKeyword(String keyword) {
        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 'sc-2d0320b9-0 gHqeOl')]//h3"));
        int count = Math.min(8, items.size());

        for (int i = 0; i < count; i++) {
            String title = items.get(i).getText().toLowerCase();
            System.out.println("Item " + (i + 1) + ": " + title);
            if (title.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private void closePopupIfPresent() {
        try {
            WebElement closeBtn = driver.findElement(By.xpath("//img[@alt='close-icon']"));
            closeBtn.click();
        } catch (Exception e) {
            System.out.println("Không có popup hiển thị.");
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
