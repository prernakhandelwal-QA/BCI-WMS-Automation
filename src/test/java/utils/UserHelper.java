package utils;

import com.microsoft.playwright.Page;
import pages.login.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class UserHelper {

    public static Map<String, String> createUser(Page page, String baseUrl) {

        LoginPage loginPage = new LoginPage(page);
//        UserManagementPage userPage = new UserManagementPage(page);
//
//        long uniqueId = System.currentTimeMillis();
//
//        Map<String, String> userData = new HashMap<>();
//
//        userData.put("first_name", "Auto");
//        userData.put("last_name", "User");
//        userData.put("phone_number", "9876543210");
//        userData.put("user_code", "AUTO" + uniqueId);
//        userData.put("email", "auto" + uniqueId + "@test.com");
//        userData.put("password", "Bcil@" + uniqueId);

        loginPage.navigate(baseUrl);
        loginPage.login("B1350", "Bcil@12345678");

//        userPage.openUserRoleAssignment();
//        userPage.clickAddUser();
//
//        userPage.fillUserForm(
//                userData.get("first_name"),
//                userData.get("last_name"),
//                userData.get("phone_number"),
//                userData.get("user_code"),
//                userData.get("email"),
//                userData.get("password"),
//                userData.get("password")
//        );
//
//        userPage.selectUserType("Supervisor");
//        userPage.selectReportingManager("TCF - ADMIN");
//        userPage.selectRole();
//        userPage.clickAdd();

        loginPage.logout();

//        return userData;
        return null;
    }
}