package tests.usermanagement;

import base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.usermanagement.RoleModuleRightsPage;

@Listeners(utils.TestListener.class)

public class CreateRoleTest extends BaseTest {

    @Test
    public void testCreateRole() {

        LoginPage loginPage = new LoginPage(page);
        RoleModuleRightsPage rolePage = new RoleModuleRightsPage(page);

        // ✅ Generate dynamic data
        long uniqueId = System.currentTimeMillis();

        String roleName = "AUTO_ROLE_" + uniqueId;
        String displayName = "Auto Role " + uniqueId;
        String description = "Automation created role";

        // ✅ Step 1: Login
        loginPage.navigate(baseUrl);
        loginPage.login("B1350", "Bcil@123456789");

        // ✅ Step 2: Open Role Module Rights
        rolePage.openRoleModuleRights();

        // ✅ Step 3: Create Role
        rolePage.clickAddRole();
        rolePage.fillRoleDetails(
                roleName,
                displayName,
                description
        );

        // ✅ Step 4: Select Permission
        rolePage.selectPermission(
                "Masters - User Management - Role Master",
                "Add"
        );

        // ✅ Step 5: Save
        rolePage.clickSave();

        // ✅ Step 6: Validate
        rolePage.verifyRoleCreated(roleName);
    }
}