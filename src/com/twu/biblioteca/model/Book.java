package com.twu.biblioteca.model;

public class Book {
    private String name;
    private String author;
    private String publishedYear;
    private String userLibraryNumber;
    private boolean checkedOut;

    public Book(String name, String author,String publishedYear) {

        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public String getUserLibraryNumber() {
        return userLibraryNumber;
    }

    public void setUserLibraryNumber(String userLibraryNumber) {
        this.userLibraryNumber = userLibraryNumber;
    }
}
