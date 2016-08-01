package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.impl.IActionHandler;
import com.twu.biblioteca.router.instance.CheckoutActionHandler;
import com.twu.biblioteca.router.instance.InitializeActionHandler;
import com.twu.biblioteca.router.instance.MainMenuActionHandler;
import com.twu.biblioteca.router.instance.ReturnActionHandler;
import com.twu.biblioteca.service.BibliotecaService;

import java.security.NoSuchProviderException;


/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaRouter {
    private BibliotecaService myService;
    private RouterContext myContext;

    public BibliotecaRouter(RouterState initialState, BibliotecaService service) {
        this.myService = service;
        this.myContext = new RouterContext(initialState);
    }

    public RouterMessage GetRouterMessage(String userInput) throws NoSuchProviderException {
        return GetActionHandler().Handle(userInput);
    }

    IActionHandler GetActionHandler() throws NoSuchProviderException {
        if(myContext.getCurrentState() == RouterState.Initializing) {
            return new InitializeActionHandler(myContext, myService);
        }
        if (myContext.getCurrentState() == RouterState.MainMenu) {
            return new MainMenuActionHandler(myContext, myService);
        }
        if(myContext.getCurrentState() == RouterState.Checkout) {
            return new CheckoutActionHandler(myContext, myService);
        }
        if (myContext.getCurrentState() == RouterState.Return){
            return new ReturnActionHandler(myContext, myService);
        }
        throw new NoSuchProviderException("Not supported error");
    }
}
