package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.router.IActionHandler;
import com.twu.biblioteca.service.BibliotecaService;


public class InitializeActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public InitializeActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }

    @Override
    public RouterMessage handle(String userInput) {
        myContext.setNestState(RouterState.MainMenu);
        return new RouterMessage(myService.getWelcomeMessage(),false,false);
    }
}
