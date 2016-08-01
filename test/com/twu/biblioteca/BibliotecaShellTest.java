package com.twu.biblioteca;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.router.BibliotecaRouter;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.service.BibliotecaService;
import com.twu.biblioteca.shell.BibliotecaShell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.NoSuchProviderException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        bibliotecaShell = new BibliotecaShell(System.in,outStream);
        System.setOut(outStream);
    }

    @After
    public void setDown() throws Exception{
        System.setOut(outStream);
    }

    @Test
    public void should_display_welcome_message_when_current_state_is_initializing() throws IOException, NoSuchProviderException {
        bibliotecaShell.Execute();
        assertTrue(outContent.toString().contains("Welcome To Biblioteca Library!"));
    }

    @Test
    public void should_display_main_menu_when_current_state_is_main_menu() throws IOException, NoSuchProviderException {
        bibliotecaShell.Execute();
        assertTrue(outContent.toString().contains("****         This is our Main Menu       ****\n" +
                "*********************************************\n****1.       List Books                  ****\n********************" +
                "*************************\nplease input what your choose:\n"));
    }

    @Test
    public void should_display_book_list_when_current_state_is_main_menu_and_user_input_is_list_books() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage("1");

        assertEquals(message.getUserInput(),"****          All Book Detials           ****\n" +
                "*********************************************\n****    Name   PublishedYear  Author     ****\n" +
                "*********************************************\n****    math  yangliu  2013-10-10\n" +
                "****    chinese  huawu  2011-09-14\n****    english  danhu  2015-05-10\n****         This is our Main " +
                "Menu       ****\n*********************************************\n****1.       List Books                 " +
                " ****\n*********************************************\nplease input what your choose:\n");
    }

    @Test
    public void should_display_an_invalid_message_and_main_menu_when_user_input_is_not_list_books_and_current_state_is_main_menu() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage("invalid input");

        assertEquals(message.getUserInput(),"Invalid input, Please try again\r\n\r\n****         This is our Main Menu       ****\n" +
                "*********************************************\n****1.       List Books                  ****\n********************" +
                "*************************\nplease input what your choose:\n");
    }

    @Test
    public void should_quit_when_user_input_is_quit_and_current_state_is_main_menu() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage("0");

        assertTrue(message.isExit());
        assertFalse(message.isWaitForInput());
    }

    @Test
    public void should_waiting_for_user_input_when_user_select_checkout_books_and_current_status_is_main_menu() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage("1");

        assertTrue(message.isWaitForInput());
    }

}
