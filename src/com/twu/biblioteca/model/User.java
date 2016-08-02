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

    public User(String libraryNumber, String password){
        this.libraryNumber = libraryNumber;
        this.password = password;
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

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
