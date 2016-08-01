package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.router.impl.IActionHandler;
import com.twu.biblioteca.router.resource.BibliotecaMenu;
import com.twu.biblioteca.service.BibliotecaService;

import java.util.List;

public class MainMenuActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;
    private BibliotecaMenu mainMenu;

    public MainMenuActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
        mainMenu = new BibliotecaMenu();
    }

    @Override
    public RouterMessage Handle(String userInput) {
        if (userInput==null) {
            return new RouterMessage(mainMenu.GetMainMenu(),false,true);
        }
        if (userInput.equals("0")) {
            return new RouterMessage("",true,false);
        }
        if (userInput.equals("1")) {
            List<Book> myAllBooks = myService.ListBooks();
            return new RouterMessage(FomartListBooks(myAllBooks) + mainMenu.GetMainMenu(),false,true);
        }
        if (userInput.equals("2")) {
            myContext.setNestState(RouterState.Checkout);
            return new RouterMessage("",false,true);
        }
        if (userInput.equals("3")) {
            myContext.setNestState(RouterState.Return);
            return new RouterMessage("",false,true);
        }
        return new RouterMessage("Invalid input, Please try again\n\n" + mainMenu.GetMainMenu(),false,true);
    }

    private String FomartListBooks(List<Book> myAllBooks) {
        StringBuilder st = new StringBuilder();
        st.append("****          All Book Detials           ****\n")
                .append("*********************************************\n")
                .append("****    Name   PublishedYear  Author     ****\n")
                .append("*********************************************\n");
        for (Book book : myAllBooks) {
            st.append("****    " + book.getName() + "  " + book.getAuthor() + "  " + book.getPublishedYear() + "\n");

        }
        return st.toString();
    }
}
