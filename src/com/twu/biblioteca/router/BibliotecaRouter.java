package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.service.BibliotecaService;


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

    public RouterMessage GetRouterMessage() {
        if(myContext.getCurrentState() == RouterState.Initializing){
            return new RouterMessage(myService.GetWelcomeMessage());
        }
        return null;
    }
}
