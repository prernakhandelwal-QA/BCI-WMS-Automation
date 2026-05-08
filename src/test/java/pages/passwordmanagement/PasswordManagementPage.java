package pages.passwordmanagement;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PasswordManagementPage {

    private final Page page;

    private final Locator mastersMenu;
    private final Locator userManagementMenu;
    private final Locator passwordManagementMenu;

    private final Locator resetPasswordInput;
    private final Locator confirmPasswordInput;
    private final Locator submitButton;

    public PasswordManagementPage(Page page) {
        this.page = page;

        this.mastersMenu = page.getByText("Masters", new Page.GetByTextOptions().setExact(true));
        this.userManagementMenu = page.getByText("User Management", new Page.GetByTextOptions().setExact(true));
        this.passwordManagementMenu = page.getByText("Password Master", new Page.GetByTextOptions().setExact(true));

        this.resetPasswordInput = page.locator("input[name='password']");
        this.confirmPasswordInput = page.locator("input[name='confirmPassword']");
        this.submitButton = page.locator("button:has-text('Submit')");
    }

    public void openPasswordManagement() {
        mastersMenu.click();
        userManagementMenu.click();
        passwordManagementMenu.click();
    }

    public void clickViewIconForUser(String username) {
        Locator row = page.locator("//tr[.//td[contains(normalize-space(), '" + username + "')]]");
        row.locator("i:has-text('visibility')").click();
    }

    public void resetPassword(String newPassword) {
        resetPasswordInput.fill(newPassword);
        confirmPasswordInput.fill(newPassword);
        submitButton.click();
    }

    public void verifyUserPresent(String username) {
        assertThat(page.getByText(username)).isVisible();
    }
}
