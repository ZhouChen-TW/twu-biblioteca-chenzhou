package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.routerMessage;
import com.twu.biblioteca.router.IActionHandler;
import com.twu.biblioteca.service.BibliotecaService;


public class CheckoutActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public CheckoutActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public routerMessage handle(String userInput) {
        boolean checkoutSuccess = myService.checkoutBooks(userInput);
        if (checkoutSuccess)
        {
            myContext.setNestState(RouterState.MainMenu);
            return new routerMessage("Thank you! Enjoy the book!\n\n",false,false);
        }
        myContext.setNestState(RouterState.MainMenu);
        return new routerMessage("That book is not available.\n\n",false,false);
    }
}
