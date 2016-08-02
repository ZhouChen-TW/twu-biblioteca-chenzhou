package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.routerMessage;
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
    public routerMessage handle(String userInput) {
        if (userInput == null) {
            return new routerMessage(getMainMenu(), false, true);
        }
        if (userInput.equals("0")) {
            return new routerMessage("", true, false);
        }
        if (userInput.equals("1")) {
            List<Book> myAllBooks = myService.listBooks();
            return new routerMessage(formatListBooks(myAllBooks), false, false);
        }
        if (userInput.equals("2")) {
            myContext.setNestState(RouterState.Checkout);
            return new routerMessage("", false, true);
        }
        if (userInput.equals("3")) {
            myContext.setNestState(RouterState.Return);
            return new routerMessage("", false, true);
        }
        if (userInput.equals("4")) {
            List<Movie> myAllMovies = myService.listMoves();
            return new routerMessage(formatListMovies(myAllMovies), false,false);
        }
        return new routerMessage("Select a valid option!\n\n", false, false);
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

    private static String getMainMenu(){
        return "****         This is our Main Menu       ****\n" +
                "*********************************************\n" +
                "****1.       List Books                  ****\n" +
                "****2.       CheckOut Books              ****\n" +
                "****3.       Return Books                ****\n" +
                "****4.       List Movies                 ****\n" +
                "****0.       Quit                        ****\n" +
                "*********************************************\n" +
                "please input what your choose:\n";
    }
}
