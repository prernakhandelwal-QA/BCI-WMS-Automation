package utils;

import com.microsoft.playwright.Page;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        try {
            Page page = (Page) testClass
                    .getClass()
                    .getField("page")
                    .get(testClass);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());


            String fileName = result.getName() + "_" + timestamp + ".png";

            page.screenshot(
                    new Page.ScreenshotOptions()
                            .setPath(Paths.get("screenshots", fileName))
            );

            System.out.println("Screenshot saved: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}