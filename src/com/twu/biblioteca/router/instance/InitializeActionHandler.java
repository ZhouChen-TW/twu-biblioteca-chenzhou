package com.twu.biblioteca.router.instance;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.RouterContext;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.router.impl.IActionHandler;
import com.twu.biblioteca.router.resource.BibliotecaMenu;
import com.twu.biblioteca.service.BibliotecaService;

/**
 * Created by chenzhou on 8/1/16.
 */
public class InitializeActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;
    private BibliotecaMenu mainMenu;

    public InitializeActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
        mainMenu = new BibliotecaMenu();
    }

    @Override
    public RouterMessage Handle(String userInput) {
        myContext.setNestState(RouterState.MainMenu);
        return new RouterMessage(myService.GetWelcomeMessage()+mainMenu.GetMainMenu(),false,false);
    }
}
