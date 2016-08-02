package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.router.IActionHandler;
import com.twu.biblioteca.service.BibliotecaService;

import java.util.List;

public class MainMenuActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public MainMenuActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public RouterMessage handle(String userInput) {
        User user = myService.getUser();

        if (userInput == null) {
            return new RouterMessage(getMainMenu(), false, true);
        }
        if (userInput.equals("new")){
            return new RouterMessage(getMainMenuNew(), false, true);
        }
        if (userInput.equals("0")) {
            return new RouterMessage("", true, false);
        }
        if (userInput.equals("1")) {
            List<Book> myAllBooks = myService.listBooks();
            return new RouterMessage(formatListBooks(myAllBooks), false, false);
        }
        if (userInput.equals("2")) {
            if (user.isLoginState()){
                myContext.setNestState(RouterState.CheckoutBooks);
                return new RouterMessage("", false, true);
            }
            myContext.setNestState(RouterState.Login);
            return new RouterMessage("", false, true);
        }
        if (userInput.equals("3")) {
            if (user.isLoginState()){
                myContext.setNestState(RouterState.Return);
                return new RouterMessage("", false, true);
            }
            myContext.setNestState(RouterState.Login);
            return new RouterMessage("", false, true);
        }
        if (userInput.equals("4")) {
            List<Movie> myAllMovies = myService.listMoves();
            return new RouterMessage(formatListMovies(myAllMovies), false,false);
        }
        if (userInput.equals("5")) {
            if (user.isLoginState()){
                myContext.setNestState(RouterState.CheckoutMovies);
                return new RouterMessage("", false, true);
            }
            myContext.setNestState(RouterState.Login);
            return new RouterMessage("", false, true);
        }
        if (userInput.equals("6")) {
            if (user.isLoginState()){
                return new RouterMessage("",false,true);
            }
            myContext.setNestState(RouterState.Login);
            return new RouterMessage("please input your message with this format (library number,password)", false, true);
        }
        if (userInput.equals("7")) {
            if (user.isLoginState()){
                return new RouterMessage(formatUser(user),false,false);
            }
            return new RouterMessage("", false, true);
        }
        return new RouterMessage("Select a valid option!\n\n", false, false);
    }

    private String formatListBooks(List<Book> myAllBooks) {
        StringBuilder st = new StringBuilder();
        st.append("****          All Book Detials           ****\n")
                .append("*********************************************\n")
                .append("****    Name   PublishedYear  Author     ****\n")
                .append("*********************************************\n");
        for (Book book : myAllBooks) {
            st.append("****    ").append(book.getName()).append("  ").append(book.getAuthor()).append("  ").append(book.getPublishedYear()).append("\n");

        }
        return st.toString();
    }

    private String formatListMovies(List<Movie> myAllMovies) {
        StringBuilder st = new StringBuilder();
        st.append("****          All Movie Detials          ****\n")
                .append("*********************************************\n")
                .append("****    Name  Year  Director  Rating     ****\n")
                .append("*********************************************\n");
        for (Movie movie : myAllMovies) {
            st.append("****    ").append(movie.getName()).append("  ").append(movie.getYear()).append("  ").append(movie.getDirector()).append("  ").append(movie.getMovieRating()).append("\n");
        }
        return st.toString();
    }

    private String formatUser(User user){
        StringBuilder st = new StringBuilder();
        return st.append("****    ").append("name: ").append(user.getName()).append("  email address: ").append(user.getEmail()).append("  phone number: ").append(user.getPhone()).toString();
    }

    private static String getMainMenu(){
        return "****         This is our Main Menu       ****\n" +
                "*********************************************\n" +
                "****1.       List Books                  ****\n" +
                "****2.       CheckOut Books              ****\n" +
                "****3.       Return Books                ****\n" +
                "****4.       List Movies                 ****\n" +
                "****5.       CheckOut Movies             ****\n" +
                "****6.       Login                       ****\n" +
                "****0.       Quit                        ****\n" +
                "*********************************************\n" +
                "please input what your choose:\n";
    }

    private static String getMainMenuNew(){
        return "****         This is our Main Menu       ****\n" +
                "*********************************************\n" +
                "****1.       List Books                  ****\n" +
                "****2.       CheckOut Books              ****\n" +
                "****3.       Return Books                ****\n" +
                "****4.       List Movies                 ****\n" +
                "****5.       CheckOut Movies             ****\n" +
                "****6.       Login                       ****\n" +
                "****7.       User Information            ****\n" +
                "****0.       Quit                        ****\n" +
                "*********************************************\n" +
                "please input what your choose:\n";
    }
}
