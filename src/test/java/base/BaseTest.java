package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Page page;
    protected String baseUrl = "http://180.151.246.51:1037/account/login";

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    public void teardown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
