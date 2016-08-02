package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.router.instance.*;
import com.twu.biblioteca.service.BibliotecaService;

import java.security.NoSuchProviderException;


public class BibliotecaRouter {
    private BibliotecaService myService;
    private RouterContext myContext;

    public BibliotecaRouter(RouterState initialState, BibliotecaService service) {
        this.myService = service;
        this.myContext = new RouterContext(initialState);
    }

    public RouterMessage getRouterMessage(String userInput) throws NoSuchProviderException {
        return getActionHandler().handle(userInput);
    }

    private IActionHandler getActionHandler() throws NoSuchProviderException {
        if(myContext.getCurrentState() == RouterState.Initializing) {
            return new InitializeActionHandler(myContext, myService);
        }
        if (myContext.getCurrentState() == RouterState.MainMenu) {
            return new MainMenuActionHandler(myContext, myService);
        }
        if(myContext.getCurrentState() == RouterState.CheckoutBooks) {
            return new CheckoutBooksActionHandler(myContext, myService);
        }
        if (myContext.getCurrentState() == RouterState.Return){
            return new ReturnActionHandler(myContext, myService);
        }
        if(myContext.getCurrentState() == RouterState.CheckoutMovies) {
            return new CheckoutMoviesActionHandler(myContext, myService);
        }
        if(myContext.getCurrentState() == RouterState.Login) {
            return new LoginActionHandler(myContext, myService);
        }
        throw new NoSuchProviderException("Not supported error");
    }
}
