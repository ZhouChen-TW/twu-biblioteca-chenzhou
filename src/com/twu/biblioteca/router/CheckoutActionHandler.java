package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.service.BibliotecaService;

/**
 * Created by chenzhou on 8/1/16.
 */
public class CheckoutActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public CheckoutActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public RouterMessage Handle(String userInput) {
        boolean checkoutSuccess = myService.CheckoutBooks(userInput);
        if (checkoutSuccess)
        {
            myContext.setNestState(RouterState.MainMenu);
            return new RouterMessage("Thank you! Enjoy the book!\n\n"+myService.GetMainMenu(),false,true);
        }

        return new RouterMessage("",true,false);
    }
}
