package pages.login;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private Page page;

    // -------- Locators --------
    private Locator usernameInput;
    private Locator passwordInput;
    private Locator loginButton;

    private Locator profileIcon;
    private Locator resetPasswordLink;
    private Locator logoutLink;

    private Locator newPasswordInput;
    private Locator confirmPasswordInput;
    private Locator submitButton;

    private Locator errorPopup;
    private Locator closePopupButton;
    private Locator dashboardText;

    // -------- Constructor --------
    public LoginPage(Page page) {
        this.page = page;

        // Login page locators
        usernameInput = page.locator("input[name='username']");
        passwordInput = page.locator("input[name='password']");
        loginButton = page.locator("button[type='submit']");

        // Profile / popup locators
        profileIcon = page.locator("//a/img[@class='profile_image']");
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
        usernameInput.fill(username);
    }

    public void enterPassword(String password) {
        assertThat(passwordInput).isVisible();
        passwordInput.fill(password);
    }

    public void clearFields() {
        assertThat(usernameInput).isVisible();
        assertThat(passwordInput).isVisible();

        usernameInput.fill("");
        passwordInput.fill("");
    }

    // -------- Validation --------
    public void verifyDashboardVisible() {
        assertThat(dashboardText).isVisible();
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
