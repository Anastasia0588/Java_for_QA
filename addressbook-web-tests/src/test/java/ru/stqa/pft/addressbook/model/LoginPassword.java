package ru.stqa.pft.addressbook.model;

public class LoginPassword {
    private final String username;
    private final String password;

    public LoginPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
