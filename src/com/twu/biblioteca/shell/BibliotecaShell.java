package com.twu.biblioteca.shell;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.BibliotecaRouter;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.service.BibliotecaService;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaShell {
    private PrintStream myOutputStream;

    public BibliotecaShell(PrintStream myOutputStream) {
        this.myOutputStream = myOutputStream;
    }

    public void Execute() throws IOException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Initializing, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage();
        myOutputStream.write(message.getUserInput().getBytes());
    }
}
