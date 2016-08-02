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
        if(userInput == null){
            return new RouterMessage("please input your message with this format (library number,password)", false, true);
        }
        boolean checkoutLogin = myService.checkoutLogin(userInput);
        if(checkoutLogin){
            myContext.setNestState(RouterState.MainMenu);
            return new RouterMessage("new",false,false);
        }
        myContext.setNestState(RouterState.MainMenu);
        return new RouterMessage("",false,false);
    }
}
