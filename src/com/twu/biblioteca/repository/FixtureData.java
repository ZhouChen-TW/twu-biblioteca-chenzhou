package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;

public class FixtureData {

    public List<User> loadAllUserList() {
        List<User> myAllUsers = new ArrayList<User>();
        myAllUsers.add(new User("100-0001","000000","name0","email0","phone0"));
        myAllUsers.add(new User("100-0002","000001","name1","email1","phone1"));
        myAllUsers.add(new User("100-0003","000002","name2","email2","phone2"));
        return myAllUsers;
    }

    public List<Movie> loadAllMovieList() {
        List<Movie> myAllMovies = new ArrayList<Movie>();
        myAllMovies.add(new Movie("name0","1990","director0","unrated"));
        myAllMovies.add(new Movie("name1","1991","director1","1"));
        myAllMovies.add(new Movie("name2","1992","director2","2"));
        return myAllMovies;
    }

    public List<Book> loadAllBookList(){
        List<Book> myAllBooks = new ArrayList<Book>();
        myAllBooks.add(new Book("math","yangliu","2013-10-10"));
        myAllBooks.add(new Book("chinese","huawu","2011-09-14"));
        myAllBooks.add(new Book("english","danhu","2015-05-10"));
        return myAllBooks;
    }
}
