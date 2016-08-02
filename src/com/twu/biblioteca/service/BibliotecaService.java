package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;


public class BibliotecaService {
    private List<Book> myAllBooks = new ArrayList<Book>();
    private List<Movie> myAllMovies = new ArrayList<Movie>();

    public BibliotecaService() {
        loadAllBookList();
        loadAllMovieList();
    }

    private void loadAllMovieList() {
        myAllMovies.add(new Movie("name0","1990","director0","unrated"));
        myAllMovies.add(new Movie("name1","1991","director1","1"));
        myAllMovies.add(new Movie("name2","1992","director2","2"));
    }

    private List<Book> getMyAllBooks() {
        return myAllBooks;
    }

    public List<Movie> getMyAllMovies() {
        return myAllMovies;
    }

    private void loadAllBookList(){
        myAllBooks.add(new Book("math","yangliu","2013-10-10"));
        myAllBooks.add(new Book("chinese","huawu","2011-09-14"));
        myAllBooks.add(new Book("english","danhu","2015-05-10"));
    }

    public List<Movie> listMoves(){
        return  myAllMovies;
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

    public boolean checkoutMovies(String movieName) {
        for (Movie movie : getMyAllMovies()){
            if (movieName.equals(movie.getName())){
                return true;
            }
        }
        return false;
    }
}
