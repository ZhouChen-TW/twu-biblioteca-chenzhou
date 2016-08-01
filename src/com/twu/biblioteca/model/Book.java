package com.twu.biblioteca.model;

/**
 * Created by chenzhou on 8/1/16.
 */
public class Book {
    private String name;
    private boolean checkedOut;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
}
