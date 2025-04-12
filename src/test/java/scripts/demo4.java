package scripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class demo4 {
    static WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void verifyAudioAccessoryDiscount() {
        driver.get("https://tiki.vn");

        //closePopupIfPresent();

        // Click vào danh mục "Thiết bị âm thanh và phụ kiện"
        WebElement danhMuc = driver.findElement(By.xpath("//a[contains(text(),'Thiết Bị Âm Thanh & Phụ Kiện')]"));
        danhMuc.click();

        // Click vào sản phẩm đầu tiên
        WebElement firstProduct = driver.findElement(By.cssSelector("div[data-view-id='product_list_container'] a"));
        firstProduct.click();

        // Chuyển sang tab sản phẩm
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }

        // Kiểm tra có giá gốc và giá đang giảm
        WebElement salePrice = driver.findElement(By.cssSelector(".product-price__current-price"));
        WebElement originalPrice = driver.findElement(By.cssSelector(".product-price__sub-price"));

        Assert.assertTrue(salePrice.isDisplayed(), "Không có giá giảm");
        Assert.assertTrue(originalPrice.isDisplayed(), "Không có giá gốc");
    }

}
