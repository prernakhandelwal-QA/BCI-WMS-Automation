package models;

public class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userCode;
    private String email;
    private String password;

    public User(String firstName, String lastName, String phoneNumber,
                String userCode, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userCode = userCode;
        this.email = email;
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getPassword() {
        return password;
    }

    // Optional (good practice)
    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}