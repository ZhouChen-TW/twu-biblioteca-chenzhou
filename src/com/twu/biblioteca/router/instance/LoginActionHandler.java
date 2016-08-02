package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.IActionHandler;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.service.BibliotecaService;

public class LoginActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public LoginActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public RouterMessage handle(String userInput) {
        boolean checkoutLogin = myService.checkoutLogin(userInput);
        if(checkoutLogin){
            myContext.setNestState(RouterState.MainMenu);
            return new RouterMessage("",false,false);
        }
        myContext.setNestState(RouterState.MainMenu);
        return new RouterMessage("",false,false);
    }
}
