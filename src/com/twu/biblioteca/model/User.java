package com.twu.biblioteca.model;

public class User {
    private String libraryNumber;
    private String password;
    private boolean loginState;

    public User(String libraryNumber, String password){
        this.libraryNumber = libraryNumber;
        this.password = password;
    }
    public User(boolean loginState){
        this.loginState = loginState;
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
}
