package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.service.BibliotecaService;

/**
 * Created by chenzhou on 8/1/16.
 */
public class ReturnActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public ReturnActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public RouterMessage Handle(String userInput) {
        boolean returnSuccess = myService.ReturnBooks(userInput);
        if (returnSuccess)
        {
            myContext.setNestState(RouterState.MainMenu);
            return new RouterMessage("Thank you for returning the book.\n\n",false,true);
        }

        return new RouterMessage("",false,true);
    }
}
