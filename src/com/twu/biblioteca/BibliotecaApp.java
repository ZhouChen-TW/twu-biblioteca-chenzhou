package com.twu.biblioteca;

import com.twu.biblioteca.shell.BibliotecaShell;

import java.io.IOException;
import java.security.NoSuchProviderException;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException, NoSuchProviderException {
        new BibliotecaShell(System.in,System.out).execute();
    }
}
