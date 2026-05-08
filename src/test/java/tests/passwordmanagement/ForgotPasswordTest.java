package tests.passwordmanagement;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import models.User;
import org.testng.annotations.*;
import pages.login.ForgotPasswordPage;
import pages.login.LoginPage;
import pages.passwordmanagement.PasswordManagementPage;
import utils.UserHelper;

@Listeners(utils.TestListener.class)

public class ForgotPasswordTest extends BaseTest {

    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private PasswordManagementPage passwordManagementPage;

    private User createdUser;

    // ✅ SETUP → Create user
    @BeforeMethod
    public void setUpTest() {

        loginPage = new LoginPage(page);
        forgotPasswordPage = new ForgotPasswordPage(page);
        passwordManagementPage = new PasswordManagementPage(page);

        // ✅ Create user dynamically
        createdUser = UserHelper.createUser(page, baseUrl);
    }

    // ✅ TEST → Forgot password flow
    @Test
    public void testPasswordManagementFlow() {

        String userCode = createdUser.getUserCode();
        String newPassword = "Bcil@" + System.currentTimeMillis();

        // Step 1: Forgot password
        loginPage.navigate(baseUrl);
        forgotPasswordPage.openForgotPassword(userCode);
        forgotPasswordPage.submitForgotPasswordRequest(userCode);

        // Step 2: Admin login
        loginPage.navigate(baseUrl);
        loginPage.login("B1350", "Bcil@123456789");

        // Step 3: Reset password
        passwordManagementPage.openPasswordManagement();
        passwordManagementPage.clickViewIconForUser(userCode);
        passwordManagementPage.resetPassword(newPassword);

        // ✅ Logout admin
        loginPage.logout();

        // Step 4: Login with new password
        loginPage.navigate(baseUrl);
        loginPage.login(userCode, newPassword);

        // URL validation
        loginPage.verifyDashboardPageVisible();
        System.out.println("Login successful with the new password ✅");
    }

    // ✅ TEARDOWN → Delete user
    @AfterMethod
    public void tearDownTest() {

        try {
            UserHelper.deleteUser(page, baseUrl, createdUser.getUserCode());
        } catch (Exception e) {
            System.out.println("Cleanup failed: " + e.getMessage());
        }
    }
}