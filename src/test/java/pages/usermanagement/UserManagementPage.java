package pages.usermanagement;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.options.AriaRole;

public class UserManagementPage {

    private final Page page;

    private final Locator mastersMenu;
    private final Locator userManagementMenu;
    private final Locator userRoleAssignmentMenu;

    private final Locator plusIcon;

    public final Locator firstNameInput;
    public final Locator lastNameInput;
    public final Locator phoneNumberInput;
    public final Locator userCodeInput;
    public final Locator userTypeDropdown;
    public final Locator designation;
    public final Locator reportingManagerDropdown;
    public final Locator plant;
    public final Locator emailInput;
    public final Locator passwordInput;
    public final Locator confirmPasswordInput;

    private final Locator roleCheckbox;
    private final Locator addButton;

    public UserManagementPage(Page page) {
        this.page = page;

        this.mastersMenu = page.getByText("Masters", new Page.GetByTextOptions().setExact(true));
        this.userManagementMenu = page.getByText("User Management", new Page.GetByTextOptions().setExact(true));
        this.userRoleAssignmentMenu = page.getByText("User Master", new Page.GetByTextOptions().setExact(true));

        this.plusIcon = page.locator("//i[text()='add_circle']");

        this.firstNameInput = page.locator("input[name='firstName']");
        this.lastNameInput = page.locator("input[name='lastName']");
        this.phoneNumberInput = page.locator("input[name='phoneNumber']");
        this.userCodeInput = page.locator("input[name='userName']");
        this.userTypeDropdown = page.getByLabel("Select User Type");
        this.designation = page.getByLabel("Select Designation");
        this.reportingManagerDropdown = page.getByLabel("Select Reporting Manager");
        this.plant = page.getByLabel("Select Plant");
        this.emailInput = page.locator("input[name='email']");
        this.passwordInput = page.locator("input[name='password']");
        this.confirmPasswordInput = page.locator("input[name='confirmPassword']");

        this.roleCheckbox = page.locator("//label[normalize-space()='Admin']/ancestor::mat-checkbox");

        // ✅ safer locator instead of getByRole issue
        this.addButton = page.locator("button:has-text('Add')");
    }

    public void openUserRoleAssignment() {
        mastersMenu.click();
        userManagementMenu.click();
        userRoleAssignmentMenu.click();
    }

    public void clickAddUser() {
        plusIcon.click();
    }

    public void fillUserForm(
            String firstName,
            String lastName,
            String phoneNumber,
            String userCode,
            String email,
            String password,
            String confirmPassword
    ) {
        firstNameInput.fill(firstName);
        lastNameInput.fill(lastName);
        phoneNumberInput.fill(phoneNumber);
        userCodeInput.click();
        userCodeInput.fill(userCode);
        emailInput.fill(email);
        passwordInput.fill(password);
        confirmPasswordInput.fill(confirmPassword);
    }

    public void selectUserType(String userType) {
        userTypeDropdown.click();
        page.getByRole(AriaRole.OPTION,
                new Page.GetByRoleOptions().setName(userType)
        ).click();

    }

    public void selectDesignation(String Designation) {
        designation.click();
        page.getByRole(AriaRole.OPTION,
                new Page.GetByRoleOptions().setName(Designation)
        ).click();
//        page.getByText(Designation, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void selectPlant(String Plant) {
        plant.click();

        page.getByRole(AriaRole.OPTION,
                new Page.GetByRoleOptions().setName(Plant).setExact(true)).click();

//        page.getByText(Plant, new Page.GetByTextOptions().setExact(true)).click();
    }

    public void selectReportingManager(String managerName) {
        reportingManagerDropdown.click();
        page.getByRole(AriaRole.OPTION,
                new Page.GetByRoleOptions().setName(managerName)
        ).click();
//        page.getByText(managerName, new Page.GetByTextOptions().setExact(true)).click();
        page.keyboard().press("Escape");
    }

    public void selectRole() {
        roleCheckbox.click();
    }

    public void clickAdd() {
        addButton.click();
    }

    public void verifyUserPresentInGrid(String userCode) {
        assertThat(page.getByText(userCode, new Page.GetByTextOptions().setExact(true)))
                .isVisible();
    }
}