package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BibliotecaService {
    private List<Book> myAllBooks = new ArrayList<Book>();

    public BibliotecaService() {
        loadAllBookList();
    }

    private List<Book> getMyAllBooks() {
        return myAllBooks;
    }

    private void loadAllBookList(){
        myAllBooks.add(new Book("math","yangliu","2013-10-10"));
        myAllBooks.add(new Book("chinese","huawu","2011-09-14"));
        myAllBooks.add(new Book("english","danhu","2015-05-10"));
    }

    public String getWelcomeMessage() {
        return "Welcome To Biblioteca Library!\n";
    }

    public List<Book> listBooks() {
        List<Book> myAvaiableBooks = new ArrayList<Book>();
        for (Book book : getMyAllBooks()) {
            if (!book.isCheckedOut()) {
                myAvaiableBooks.add(book);
            }
        }
        return myAvaiableBooks;
    }

    public boolean checkoutBooks(String bookName) {
        if(bookName == null || bookName.isEmpty()){
            return false;
        }
        for (Book book : getMyAllBooks()) {
            if (!book.isCheckedOut() && bookName.equals(book.getName())){
                    book.setCheckedOut(true);
                    return true;
            }
        }
        return false;
    }

    public boolean returnBooks(String bookName) {
        if(bookName == null || bookName.isEmpty()){
            return false;
        }
        for (Book book : getMyAllBooks()) {
            if (bookName.equals(book.getName()) && book.isCheckedOut()) {
                book.setCheckedOut(false);
                return true;
            }
        }
        return false;

    }
}
