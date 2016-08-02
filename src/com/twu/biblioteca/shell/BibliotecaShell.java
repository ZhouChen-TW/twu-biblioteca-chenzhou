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

public class BibliotecaShell {
    private InputStream myInputStream;
    private PrintStream myOutputStream;

    public BibliotecaShell(InputStream myInputStream, PrintStream myOutputStream) {
        this.myInputStream = myInputStream;
        this.myOutputStream = myOutputStream;
    }

    public void execute() throws IOException, NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Initializing, new BibliotecaService());

        String userInput = null;
        while (true) {
            RouterMessage message = router.getRouterMessage(userInput);
            myOutputStream.write(message.getUserInput().getBytes());
            if (message.isExit()) {
                break;
            }
            if (message.isWaitForInput()) {
                userInput = new Scanner(myInputStream).next();
            } else {
                userInput = null;
            }
        }
    }
}
