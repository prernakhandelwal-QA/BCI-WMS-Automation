package pages.passwordmanagement;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ResetPasswordPage {

    private final Page page;

    private final Locator profileIcon;
    private final Locator resetPasswordLink;
    private final Locator newPasswordInput;
    private final Locator confirmPasswordInput;
    private final Locator submitButton;

    public ResetPasswordPage(Page page) {
        this.page = page;

        this.profileIcon = page.locator("//a/i[text()='person']");
        this.resetPasswordLink = page.getByText("Reset Password");
        this.newPasswordInput = page.locator("//input[@name='password']");
        this.confirmPasswordInput = page.locator("//input[@name='confirmPassword']");
        this.submitButton = page.locator("//button[normalize-space()='Submit']");
    }

    public void openResetPassword() {
        profileIcon.click();
        resetPasswordLink.click();
    }

    public void resetPassword(String newPassword, String confirmPassword) {
        // Equivalent of expect(...).to_be_visible()
        assertThat(newPasswordInput).isVisible();

        newPasswordInput.fill(newPassword);
        confirmPasswordInput.fill(confirmPassword);
        submitButton.click();
    }
}