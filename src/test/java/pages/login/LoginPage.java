package pages.login;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private Page page;

    // -------- Locators --------
    public Locator usernameInput;
    public Locator passwordInput;
    public Locator loginButton;

    public Locator profileIcon;
    public Locator resetPasswordLink;
    public Locator logoutLink;

    public Locator newPasswordInput;
    public Locator confirmPasswordInput;
    public Locator submitButton;

    public Locator errorPopup;
    public Locator closePopupButton;
    public Locator dashboardText;

    // -------- Constructor --------
    public LoginPage(Page page) {
        this.page = page;

        // Login page locators
        usernameInput = page.locator("input[name='username']");
        passwordInput = page.locator("input[name='password']");
        loginButton = page.locator("button[type='submit']");

        // Profile / popup locators
        profileIcon = page.locator("//a/i[text()='person']");
        resetPasswordLink = page.getByText("Reset Password");
        logoutLink = page.getByText("Logout");

        newPasswordInput = page.locator("input[name='newPassword']");
        confirmPasswordInput = page.locator("input[name='confirmPassword']");

        submitButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Submit")
        );

        errorPopup = page.locator("text=Invalid username or password!");
        closePopupButton = page.locator("button:has-text('Close')");
        dashboardText = page.getByText("Dashboard");
    }

    // -------- Navigation --------
    public void navigate(String url) {
        page.navigate(url);
    }

    // -------- Login Actions --------
    public void login(String username, String password) {
        assertThat(usernameInput).isVisible();
        usernameInput.click();
        usernameInput.fill(username);

        assertThat(passwordInput).isVisible();
        passwordInput.click();
        passwordInput.fill(password);

        loginButton.click();
    }

    public void enterUsername(String username) {
        assertThat(usernameInput).isVisible();
        usernameInput.click();
        usernameInput.fill(username);
    }

    public void enterPassword(String password) {
        assertThat(passwordInput).isVisible();
        passwordInput.click();
        passwordInput.fill(password);
    }

    public void clearFields() {
        assertThat(usernameInput).isVisible();
        assertThat(passwordInput).isVisible();

        usernameInput.fill("");
        passwordInput.fill("");
    }

    // -------- Validation --------

    public void verifyDashboardPageVisible() {
        assertThat(page)
                .hasURL("http://180.151.246.51:1037/app/dashboard");
    }


    public void verifyHomePageUrl() {
        assertThat(page).hasURL("**/app/home");
    }

    public void verifyLoginButtonEnabled() {
        assertThat(loginButton).isEnabled();
    }

    public void verifyLoginButtonDisabled() {
        assertThat(loginButton).isDisabled();
    }

    // -------- Profile / Reset / Logout --------
    public void openProfileMenu() {
        assertThat(profileIcon).isVisible();
        profileIcon.click();
    }

    public void clickResetPassword() {
        openProfileMenu();
        assertThat(resetPasswordLink).isVisible();
        resetPasswordLink.click();
    }

    public void resetPassword(String newPassword, String confirmPassword) {
        assertThat(newPasswordInput).isVisible();
        newPasswordInput.fill(newPassword);

        assertThat(confirmPasswordInput).isVisible();
        confirmPasswordInput.fill(confirmPassword);

        submitButton.click();
    }

    public void logout() {
        openProfileMenu();
        assertThat(logoutLink).isVisible();
        logoutLink.click();
    }

    // -------- Error Handling --------
    public void verifyErrorPopupVisible() {
        assertThat(errorPopup).isVisible();
    }

    public void closeErrorPopup() {
        assertThat(closePopupButton).isVisible();
        closePopupButton.click();
    }
}
