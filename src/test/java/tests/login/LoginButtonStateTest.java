package tests.login;

import base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import pages.login.LoginPage;

@Listeners(utils.TestListener.class)

public class LoginButtonStateTest extends BaseTest {

    @Test
    public void testLoginButtonEnableDisable() {

        LoginPage loginPage = new LoginPage(page);

        // ✅ Navigate to login page
        loginPage.navigate(baseUrl);

        // ✅ Initially disabled
        PlaywrightAssertions.assertThat(loginPage.loginButton).isDisabled();

        // ✅ Enter username
        loginPage.enterUsername("B1350");

        PlaywrightAssertions.assertThat(loginPage.loginButton).isDisabled();

        // ✅ Enter password
        loginPage.enterPassword("Bcil@12345678");

        PlaywrightAssertions.assertThat(loginPage.loginButton).isEnabled();

        // ✅ Clear fields
        loginPage.clearFields();

        PlaywrightAssertions.assertThat(loginPage.loginButton).isDisabled();

        System.out.println("Buttons states checked successfully");
    }
}