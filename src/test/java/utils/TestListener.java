package utils;

import com.microsoft.playwright.Page;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        try {
            Object testInstance = result.getInstance();

            // ✅ Get page from BaseTest (superclass)
            Field field = testInstance.getClass().getSuperclass().getDeclaredField("page");
            field.setAccessible(true);

            Page page = (Page) field.get(testInstance);

            if (page != null) {

                String dir = "screenshots";
                new File(dir).mkdirs();

                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

                String filePath = dir + "/" + result.getName()
                        + "_" + timestamp + ".png";

                page.screenshot(new Page.ScreenshotOptions()
                        .setPath(java.nio.file.Paths.get(filePath)));

                System.out.println("📸 Screenshot saved at: " + filePath);
            }

        } catch (Exception e) {
            System.out.println("❌ Screenshot capture failed");
            e.printStackTrace();
        }
    }
}