package com.twu.biblioteca.router;

import com.twu.biblioteca.service.BibliotecaService;

/**
 * Created by chenzhou on 8/1/16.
 */
public class MainMenuActionHandler implements IActionHandler {
    private RouterContext myContext;
    private BibliotecaService myService;

    public MainMenuActionHandler(RouterContext myContext, BibliotecaService myService) {
        this.myContext = myContext;
        this.myService = myService;
    }


    @Override
    public RouterMessage Handle(String userInput) {
        if (userInput==null) {
            return new RouterMessage(myService.GetMainMenu(),false,true);
        }
        if (userInput.equals("1")) {
            return new RouterMessage(myService.ListBooks()+myService.GetMainMenu(),false,true);
        }
        if (userInput.equals("0")) {
            return new RouterMessage("",true,false);
        }
        return new RouterMessage("Invalid input, Please try again\r\n\r\n"+myService.GetMainMenu(),false,true);
    }
}
