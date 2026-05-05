package tests.login;

import base.BaseTest;
import org.testng.annotations.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import pages.login.LoginPage;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.UserHelper;

import java.util.Map;

@Listeners(utils.TestListener.class)

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private String baseUrl;

    @BeforeMethod
    public void setUpTest() {
        setup();
        loginPage = new LoginPage(page);

        // ✅ Read from config properly
        baseUrl = ConfigReader.get("base.url");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedResult) {

        loginPage.navigate(baseUrl);
        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("success")) {
            PlaywrightAssertions.assertThat(page).hasURL("**/app/dashboard");
            loginPage.verifyDashboardVisible();
        } else {
            loginPage.verifyErrorPopupVisible();
            loginPage.closeErrorPopup();
        }
    }

    // ✅ NEW test using your "created_user" equivalent
    @Test
    public void testLoginWithCreatedUser() {

        // ✅ Create user dynamically (your conftest logic)
        Map<String, String> user = UserHelper.createUser(page, baseUrl);

        String email = user.get("email");
        String password = user.get("password");

        loginPage.navigate(baseUrl);
        loginPage.login(email, password);

        PlaywrightAssertions.assertThat(page).hasURL("**/app/dashboard");
        loginPage.verifyDashboardVisible();
    }


    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelReader.getLoginData("login_data.xlsx", "Sheet1");
    }


    @AfterMethod
    public void tearDownTest() {
        tearDown();
    }
}