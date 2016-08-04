package com.twu.biblioteca.model;

public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phone;
    private boolean loginState;

    public User(boolean loginState){
        this.loginState = loginState;
    }

    public User(String libraryNumber, String password, boolean loginState){
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.loginState = loginState;
    }
    public User(String libraryNumber, String password, String name, String email, String phone){
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
