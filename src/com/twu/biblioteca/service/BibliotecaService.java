package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;

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
        loadAllBookList();
        loadAllMovieList();
        loadAllUserList();
    }

    private void loadAllUserList() {
        myAllUsers.add(new User("100-0001","000000"));
        myAllUsers.add(new User("100-0002","000001"));
        myAllUsers.add(new User("100-0003","000002"));
    }

    private void loadAllMovieList() {
        myAllMovies.add(new Movie("name0","1990","director0","unrated"));
        myAllMovies.add(new Movie("name1","1991","director1","1"));
        myAllMovies.add(new Movie("name2","1992","director2","2"));
    }

    private void loadAllBookList(){
        myAllBooks.add(new Book("math","yangliu","2013-10-10"));
        myAllBooks.add(new Book("chinese","huawu","2011-09-14"));
        myAllBooks.add(new Book("english","danhu","2015-05-10"));
    }

    private List<Book> getMyAllBooks() {
        return myAllBooks;
    }

    public List<Movie> getMyAllMovies() {
        return myAllMovies;
    }

    public List<User> getMyAllUsers() {
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
        List<Book> myAvaiableBooks = new ArrayList<Book>();
        for (Book book : getMyAllBooks()) {
            if (!book.isCheckedOut()) {
                myAvaiableBooks.add(book);
            }
        }
        return myAvaiableBooks;
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
                    getUser().setLoginState(true);
                    return true;
                }
            }
        }
        return false;
    }
}
