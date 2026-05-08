package utils;
import com.microsoft.playwright.Locator;
import models.User;
import pages.login.LoginPage;
import pages.usermanagement.UserManagementPage;
import com.microsoft.playwright.Page;

public class UserHelper {

    public static User createUser(Page page, String baseUrl) {

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
        loginPage.login("B1350", "Bcil@12345678");

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

        System.out.println("Created User:"+ userCode);

        // ✅ Safe logout
        loginPage.logout();

        return new User(firstName, lastName, phoneNumber, userCode, email, password);
    }
    public static void deleteUser(Page page, String baseUrl, String userCode) {

          UserManagementPage userManagementPage = new UserManagementPage(page);
  /*      LoginPage loginPage = new LoginPage(page);

        // ✅ Login as admin
        loginPage.navigate(baseUrl);
        loginPage.login("B1350", "Bcil@12345678");*/

        // ✅ Navigate to user list
        userManagementPage.openUserRoleAssignment();

        // ✅ Search / locate user row
        Locator row = page.locator("//tr[.//td[contains(normalize-space(), '" + userCode + "')]]");

        // ✅ Click delete icon (adjust selector based on your UI)
        row.locator("i:has-text('delete')").click();

        // ✅ Confirm delete (if popup exists)
        page.locator("//button[text()='Confirm']").click();
    }
}