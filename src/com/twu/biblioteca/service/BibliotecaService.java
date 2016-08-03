package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.repository.FixtureData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BibliotecaService {
    private List<Book> myAllBooks = new ArrayList<Book>();
    private List<Movie> myAllMovies = new ArrayList<Movie>();
    private List<User> myAllUsers = new ArrayList<User>();
    private User user;

    public BibliotecaService() {
        myAllBooks = new FixtureData().loadAllBookList();
        myAllMovies = new FixtureData().loadAllMovieList();
        myAllUsers = new FixtureData().loadAllUserList();
        user = new User(false);
    }

    private List<Book> getMyAllBooks() {
        return myAllBooks;
    }

    private List<Movie> getMyAllMovies() {
        return myAllMovies;
    }

    private List<User> getMyAllUsers() {
        return myAllUsers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWelcomeMessage() {
        return "Welcome To Biblioteca Library!\n";
    }

    public List<Book> listBooks() {
        List<Book> myAvailableBooks = new ArrayList<Book>();
        for (Book book : getMyAllBooks()) {
            if (!book.isCheckedOut()) {
                myAvailableBooks.add(book);
            }
        }
        return myAvailableBooks;
    }

    public List<Movie> listMoves(){
        return  myAllMovies;
    }

    public boolean checkoutBooks(String bookName) {
        if(bookName == null || bookName.isEmpty() || getUser() == null){
            return false;
        }
        for (Book book : getMyAllBooks()) {
            if (!book.isCheckedOut() && bookName.equals(book.getName())){
                    book.setCheckedOut(true);
                    book.setUserLibraryNumber(getUser().getLibraryNumber());
                    return true;
            }
        }
        return false;
    }

    public boolean returnBooks(String bookName) {
        if(bookName == null || bookName.isEmpty()|| getUser() == null){
            return false;
        }
        for (Book book : getMyAllBooks()) {
            if (bookName.equals(book.getName()) && book.isCheckedOut() && getUser().getLibraryNumber().equals(book.getUserLibraryNumber())) {
                book.setCheckedOut(false);
                return true;
            }
        }
        return false;

    }

    public boolean checkoutMovies(String movieName) {
        if (movieName == null || movieName.isEmpty() || getUser() == null){
            return false;
        }
        for (Movie movie : getMyAllMovies()){
            if (movieName.equals(movie.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean checkoutLogin(String userMessage) {
        if (userMessage == null || userMessage.isEmpty() || !userMessage.contains(",") || getUser() == null){
            return false;
        }
        String libraryNumber = userMessage.split(",")[0].trim();
        String password = userMessage.split(",")[1].trim();

        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(libraryNumber);
        if(matcher.find()){
            for (User user : getMyAllUsers()){
                if (libraryNumber.equals(user.getLibraryNumber()) && password.equals(user.getPassword())){
                    user.setLoginState(true);
                    setUser(user);
                    return true;
                }
            }
        }
        return false;
    }
}
