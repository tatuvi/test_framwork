package scripts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class demo2 {
    static WebDriver driver;

    @BeforeClass
    public void setUp(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://tiki.vn");
    }

@Test
    public void search() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // đóng popup
        driver.findElement(By.xpath("//img[@alt='close-icon']")).click();
        //click vao o search
        driver.findElement(By.xpath("//div[@class='sc-dec0a11d-1 cfhkdd']")).click();
        //input text adidas vao o search
       WebElement searchBox = driver.findElement(By.xpath("//input[@data-view-id=\"main_search_form_input\"]"));
        searchBox.sendKeys("adidas");

//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
//            By.xpath("//input[@data-view-id='main_search_form_input']")));
//    searchBox.sendKeys("adidas");

    searchBox.sendKeys(Keys.ENTER);
        //nhan enter
       searchBox.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //lay danh sach tieu de san pham
    List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class, 'sc-2d0320b9-0 gHqeOl')]//h3"));

    //List<WebElement> items = driver.findElements(By.cssSelector("div[data-view-id^='product_list_item'] a h3"));

    int count = Math.min(8, items.size());
    for (int i = 0; i<count; i++) {
        //lay tieu de va chuyen ve chu thuong
        String title = items.get(i).getText().toLowerCase();
        System.out.println("Item"+(i+1)+":"+title);
        Assert.assertTrue(title.contains("adidas"), "Item"+(i+1)+"khong chua chu adidas");
    }

    }
    @AfterClass
    public void teardown()
    {
        driver.quit();
    }

}





