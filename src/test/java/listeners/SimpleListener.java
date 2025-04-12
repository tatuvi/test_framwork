package listeners;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import scripts.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SimpleListener implements ITestListener {
    public void onTestStart(org.testng.ITestResult result) { /* compiled code */ }
    public void onTestSuccess(org.testng.ITestResult result) { /* compiled code
     */ }
    public void onTestFailure(org.testng.ITestResult result) {
        //in ra ten test case đe biet la dang chup hinh cho test nao
        System.out.println("Screenshot captured for test case: " + result.getName());

        Object currentClass = result.getInstance();
        //goi getdiver de lay webdriver đang dung cho test
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        //ep webdiver thanh TakesScreenshot
        //goi getScreenshotAs de chup anh man hinh va luu tam vao srcFile
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

           Date date = new Date();
            // tao duong dan den thu muc screenshots, ten file la ten test case.png

            String filePath = "." + File.separator + "screenshots" + File.separator + result.getName()+"_" + date.getTime() + ".png";
            File destFile = new File(filePath);
            destFile.getParentFile().mkdirs();
            //mkdirs => dam bao thu muc neu chua ton tai
            // sao chep anh tu file tam sang noi luu chinh thuc
            FileHandler.copy(srcFile, destFile);
            // in ra thong bao luu luu hinh thanh cong va duong
            System.out.println("Screenshot saved: " + filePath);

       }
        catch (IOException e)
        { e.printStackTrace();}

    }
    public void onTestSkipped(org.testng.ITestResult result) {


    }
    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult
                                                               result) {

    }
    public void onTestFailedWithTimeout(org.testng.ITestResult result) { /*
compiled code */ }
    public void onStart(org.testng.ITestContext context) { /* compiled code */ }
    public void onFinish(org.testng.ITestContext context) { /* compiled code */ }
}