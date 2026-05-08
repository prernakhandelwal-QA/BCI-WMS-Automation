package pages.usermanagement;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.Map;

public class RoleModuleRightsPage {

    private final Page page;

    private final Locator mastersMenu;
    private final Locator userManagementMenu;
    private final Locator roleModuleRightsMenu;

    private final Locator addButton;

    private final Locator roleNameInput;
    private final Locator displayNameInput;
    private final Locator descriptionInput;

    private final Locator saveButton;

    public RoleModuleRightsPage(Page page) {
        this.page = page;

        this.mastersMenu = page.getByText("Masters", new Page.GetByTextOptions().setExact(true));
        this.userManagementMenu = page.getByText("User Management", new Page.GetByTextOptions().setExact(true));

        // ✅ Fix HTML entity
        this.roleModuleRightsMenu = page.getByText("Role Master", new Page.GetByTextOptions().setExact(true));

        this.addButton = page.locator("//i[text()='add_circle']");

        this.roleNameInput = page.locator("input[name='Name']");
        this.displayNameInput = page.locator("input[name='DisplayName']");
        this.descriptionInput = page.locator("textarea[name='Description']");

        this.saveButton = page.locator("button:has-text('Add')");
    }

    public void openRoleModuleRights() {
        mastersMenu.click();
        userManagementMenu.click();
        roleModuleRightsMenu.click();
    }

    public void clickAddRole() {
        addButton.click();
    }

    public void fillRoleDetails(String roleName, String displayName, String description) {
        roleNameInput.fill(roleName);
        displayNameInput.fill(displayName);
        descriptionInput.fill(description);
    }

    public void selectPermission(String moduleText, String permissionType) {

        // ✅ Permission index mapping (same as Python)
        Map<String, Integer> permissionIndex = Map.of(
                "Add", 1,
                "Edit", 2,
                "Delete", 3,
                "View", 4,
                "Print", 5,
                "Approve", 6
        );

        int index = permissionIndex.get(permissionType);

        // ✅ Locate row dynamically
        Locator row = page.locator(
                "//tr[.//td[contains(normalize-space(), '" + moduleText + "')]]"
        );

        // ✅ Get checkbox in specific column
        Locator checkbox = row.locator("td").nth(index)
                .locator("input[type='checkbox']");

        checkbox.check();
    }

    public void clickSave() {
        saveButton.click();
    }

    public void verifyRoleCreated(String roleName) {
        assertThat(page.getByText(roleName)).isVisible();
    }
}