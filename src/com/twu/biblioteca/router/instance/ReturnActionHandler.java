package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.router.IActionHandler;
import com.twu.biblioteca.service.BibliotecaService;


public class ReturnActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public ReturnActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public RouterMessage handle(String userInput) {
        boolean returnSuccess = myService.returnBooks(userInput);
        if (returnSuccess)
        {
            myContext.setNestState(RouterState.MainMenu);
            return new RouterMessage("Thank you for returning the book.\n\n",false,false);
        }
        myContext.setNestState(RouterState.MainMenu);
        return new RouterMessage("That is not a valid book to return.\n\n",false,false);
    }
}
