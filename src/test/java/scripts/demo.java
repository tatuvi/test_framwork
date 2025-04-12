package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class demo {
    static WebDriver driver;
    @BeforeClass
    public void testclass(){
        System.out.println("Test before class running");
    }

    @BeforeTest
    public void testbefore(){
        System.out.println("Test before abc running");
    }
    @BeforeMethod
    public void testa(){
        System.out.println("Test before method running");
    }
    @Test
    public void test1(){
        System.out.println("Test1 running");

    }
    @Test
    public void test2(){
        System.out.println("Test2 running");

    }

    @AfterMethod
    public void testb(){
        System.out.println("Test after method running");
    }

    @AfterTest
    public void testbefore3(){
        System.out.println("Test after abc running");
    }
    @AfterClass
    public void testclasafter(){
        System.out.println("Test after class running");
    }


}
