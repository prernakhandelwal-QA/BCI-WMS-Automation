package tests.passwordmanagement;

import base.BaseTest;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import models.User;
import org.testng.annotations.*;
import pages.passwordmanagement.ResetPasswordPage;
import pages.login.LoginPage;
import utils.UserHelper;

@Listeners(utils.TestListener.class)

public class ResetPasswordTest extends BaseTest {

    private LoginPage loginPage;
    private ResetPasswordPage resetPasswordPage;

    private User createdUser;

    // ✅ SETUP → Create user
    @BeforeMethod
    public void setUpTest() {

        loginPage = new LoginPage(page);
        resetPasswordPage = new ResetPasswordPage(page);

        // ✅ create dynamic user
        createdUser = UserHelper.createUser(page, baseUrl);
    }

    // ✅ TEST → Reset password flow
    @Test
    public void testResetPasswordFlow() {

        String userCode = createdUser.getUserCode();
        String oldPassword = createdUser.getPassword();
        String newPassword = "Bcil@" + System.currentTimeMillis();

        // ✅ Step 1: Login with created user
        loginPage.navigate(baseUrl);
        loginPage.login(userCode, oldPassword);

        // ✅ Step 2: Open reset password
        resetPasswordPage.openResetPassword();

        // ✅ Step 3: Reset password
        resetPasswordPage.resetPassword(newPassword, newPassword);

        // ✅ Step 4: Validate redirect to login
        PlaywrightAssertions.assertThat(page).hasURL(baseUrl);

        // ✅ Step 5: Login with OLD password (should fail)
        loginPage.login(userCode, oldPassword);
        loginPage.verifyErrorPopupVisible();
        loginPage.closeErrorPopup();

        // ✅ Step 6: Login with NEW password
        loginPage.login(userCode, newPassword);

        // ✅ Final validation
        loginPage.verifyDashboardPageVisible();
        System.out.println("Login Successful for newly reset password");
    }

    // ✅ TEARDOWN → Delete created user
    @AfterMethod
    public void tearDownTest() {

        try {
            UserHelper.deleteUser(page, baseUrl, createdUser.getUserCode());
        } catch (Exception e) {
            System.out.println("Cleanup failed: " + e.getMessage());
        }
    }
}