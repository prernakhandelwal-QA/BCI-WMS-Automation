package tests.usermanagement;

import base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.usermanagement.UserManagementPage;
import pages.login.LoginPage;

@Listeners(utils.TestListener.class)

public class CreateUserTest extends BaseTest {

    @Test
    public void testCreateUser() {

        LoginPage loginPage = new LoginPage(page);
        UserManagementPage userManagementPage = new UserManagementPage(page);


        long uniqueId = System.currentTimeMillis();

        String firstName = "Auto";
        String lastName = "User";
        String phoneNumber = "9876543210";
        String userCode = "AUTO" + uniqueId;
        String email = "auto" + uniqueId + "@test.com";
        String password = "Bcil@" + uniqueId;

        // ✅ Login
        loginPage.navigate(baseUrl);
        loginPage.login("B1350", "Bcil@123456789");

        // ✅ Create user
        userManagementPage.openUserRoleAssignment();
        userManagementPage.clickAddUser();

        userManagementPage.fillUserForm(
                firstName,
                lastName,
                phoneNumber,
                userCode,
                email,
                password,
                password
        );

        userManagementPage.selectUserType("Admin");
        userManagementPage.selectReportingManager("Prerna - Khandelwal");
        userManagementPage.selectDesignation("Supervisor");
        userManagementPage.selectPlant("WHRM-MDK");
        userManagementPage.selectRole();
        userManagementPage.clickAdd();

        // ✅ Validation
        userManagementPage.verifyUserPresentInGrid(userCode);
    }
}