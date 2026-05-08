package tests.login;

import base.BaseTest;
import models.User;
import org.testng.annotations.*;
import pages.login.LoginPage;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.UserHelper;

@Listeners(utils.TestListener.class)

public class LoginTest extends BaseTest  {

    private LoginPage loginPage;
    private String baseUrl;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(page);
        // ✅ Read from config properly
        baseUrl = ConfigReader.get("base.url");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedResult) {

        loginPage.navigate(baseUrl);
        loginPage.login(username, password);

        if (expectedResult.equalsIgnoreCase("success")) {
            //Validate successful login
            loginPage.verifyDashboardPageVisible();
            System.out.println("Login Successful for existing user");
        } else {
            loginPage.verifyErrorPopupVisible();
            loginPage.closeErrorPopup();
        }
    }

    // ✅ Check login with the new user
    @Test
    public void testLoginWithCreatedUser() {

        // ✅ Create user
        User user = UserHelper.createUser(page, baseUrl);

        String Usercode = user.getUserCode();
        String password = user.getPassword();

        loginPage.navigate(baseUrl);
        loginPage.login(Usercode, password);

        //Validate successful login
        loginPage.verifyDashboardPageVisible();
        System.out.println("Login Successful for new user");

    }
    //getting multiple combination of credentials from the excel
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return ExcelReader.getLoginData("login_data.xlsx", "Sheet1");
    }
}