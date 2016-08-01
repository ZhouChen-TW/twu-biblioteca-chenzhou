package com.twu.biblioteca;

import com.twu.biblioteca.shell.BibliotecaShell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaShellTest {
    private BibliotecaShell bibliotecaShell;
    private ByteArrayOutputStream outContent;
    private PrintStream outStream;

    @Before
    public void setUp() throws Exception {
        outContent = new ByteArrayOutputStream();
        outStream = new PrintStream(outContent);
        bibliotecaShell = new BibliotecaShell(outStream);
        System.setOut(outStream);
    }

    @After
    public void setDown() throws Exception{
        System.setOut(outStream);
    }

    @Test
    public void should_display_welcome_message_when_current_state_is_initializing() throws IOException {
        bibliotecaShell.Execute();
        assertTrue(outContent.toString().contains("Welcome To Biblioteca Library!"));
    }
}
