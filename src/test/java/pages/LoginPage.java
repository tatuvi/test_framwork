package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){ this.driver = driver;}
    public void login(String userName, String pwd){
        driver.findElement(By.xpath("//a[@href='/sign-in']")).click();
        driver.findElement(By.xpath("//input[@id='taiKhoan']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='matKhau']")).sendKeys(pwd);
        //kiem tra o nho tai khoan da check chua
        // neu chua check -> thi check vao
        WebElement remember = driver.findElement(By.name("remember"));
        boolean isremember = remember.isSelected();
        // if isremember == false , di toi remember và click
        if (isremember){
            remember.click();
        }
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }

    public void inputUsername(String userName){
        driver.findElement(By.xpath("//input[@id=\"taiKhoan\"]")).sendKeys(userName);
    }

    public void inputPassword(String pwd){
        driver.findElement(By.xpath("//input[@id=\"matKhau\"]")).sendKeys(pwd);
    }

    public void verifyErrorMessageIsDisplay(){
        WebElement errorMsg = driver.findElement(By.xpath("//p[@id='matKhau-helper-text']"));
        if(errorMsg.isDisplayed()){
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
    }

}
