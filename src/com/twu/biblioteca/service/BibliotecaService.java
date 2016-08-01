package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaService {
    private List<Book> myAllBooks = new ArrayList<Book>();

    public BibliotecaService() {
        LoadAllBookList();
    }

    public List<Book> getMyAllBooks() {
        return myAllBooks;
    }

    public void LoadAllBookList(){
        myAllBooks.add(new Book("math","yangliu","2013-10-10"));
        myAllBooks.add(new Book("chinese","huawu","2011-09-14"));
        myAllBooks.add(new Book("english","danhu","2015-05-10"));
    }

    public String GetWelcomeMessage() {
        return "Welcome To Biblioteca Library!\n";
    }

    public String ListBooks() {
        StringBuilder st = new StringBuilder();
        st.append("****          All Book Detials           ****\n")
                .append("*********************************************\n")
                .append("****    Name   PublishedYear  Author     ****\n")
                .append("*********************************************\n");
        for (Book book : getMyAllBooks()) {
            if (!book.isCheckedOut()) {
                st.append("****    " + book.getName() + "  " + book.getAuthor() + "  " + book.getPublishedYear() + "\n");
            }
        }
        return st.toString();
    }

    public String GetMainMenu(){
        StringBuilder st = new StringBuilder();
        st.append("****         This is our Main Menu       ****\n")
                .append("*********************************************\n")
                .append("****1.       List Books                  ****\n")
                .append("****2.       CheckOut Books              ****\n")
                .append("****0.       Quit                        ****\n")
                .append("*********************************************\n")
                .append("please input what your choose(0-2):\n");
        return st.toString();
    }

    public boolean CheckoutBooks(String bookName) {
        for (Book book : getMyAllBooks()) {
            if (!book.isCheckedOut()) {
                if (bookName.equals(book.getName())){
                    book.setCheckedOut(true);
                    return true;
                }
            }else {
                return false;
            }
        }
        return false;
    }

    public boolean ReturnBooks(String bookName) {
        for (Book book : getMyAllBooks()) {
            if (bookName.equals(book.getName()) && book.isCheckedOut()) {
                book.setCheckedOut(false);
                return true;
            }
        }
        return false;

    }
}
