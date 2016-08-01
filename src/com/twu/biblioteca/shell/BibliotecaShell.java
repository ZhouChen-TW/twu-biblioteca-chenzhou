package com.twu.biblioteca.shell;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.BibliotecaRouter;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.service.BibliotecaService;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.NoSuchProviderException;
import java.util.Scanner;

/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaShell {
    private InputStream myInputStream;
    private PrintStream myOutputStream;

    public BibliotecaShell(InputStream myInputStream,PrintStream myOutputStream) {
        this.myInputStream = myInputStream;
        this.myOutputStream = myOutputStream;
    }

    public void Execute() throws IOException, NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Initializing, new BibliotecaService());

        String userInput = null;
        while (true)
        {
            RouterMessage message = router.GetRouterMessage(userInput);
            myOutputStream.write(message.getUserInput().getBytes());
            if (message.isExit()) { break; }
            userInput = new Scanner(myInputStream).next();
        }
    }
}