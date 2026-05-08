package pages.login;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ForgotPasswordPage {

    private final Page page;

    private final Locator forgotPasswordLink;
    private final Locator usernameInput;
    private final Locator userNameInput; // employeeCode field
    private final Locator submitButton;

    public ForgotPasswordPage(Page page) {
        this.page = page;

        this.forgotPasswordLink = page.getByText("Forgot Password?", new Page.GetByTextOptions().setExact(true));
        this.usernameInput = page.locator("input[name='username']");
        this.userNameInput = page.locator("input[name='employeeCode']");
        this.submitButton = page.getByText("Submit");

    }

    public void openForgotPassword(String username) {
        usernameInput.click();
        usernameInput.fill(username);
        forgotPasswordLink.click();
    }

    public void submitForgotPasswordRequest(String username) {
        userNameInput.click();
        userNameInput.fill(username);
        submitButton.click();
    }

    public void verifyRequestSubmitted(String expectedMessage) {
        if (expectedMessage != null && !expectedMessage.isEmpty()) {
            assertThat(page.getByText(expectedMessage)).isVisible();
        }
    }
}