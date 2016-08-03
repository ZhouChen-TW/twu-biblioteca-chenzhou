package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.IActionHandler;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.service.BibliotecaService;


public class CheckoutMoviesActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public CheckoutMoviesActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public RouterMessage handle(String userInput) {
        boolean checkoutSuccess = myService.checkoutBooks(userInput);
        if (checkoutSuccess)
        {
            myContext.setNestState(RouterState.MainMenu);
            return new RouterMessage("",false,false);
        }
        myContext.setNestState(RouterState.MainMenu);
        return new RouterMessage("",false,false);
    }
}
