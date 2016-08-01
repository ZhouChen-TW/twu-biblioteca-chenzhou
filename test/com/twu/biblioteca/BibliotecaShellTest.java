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

        StringBuilder st = new StringBuilder();
        st.append("****         This is our Main Menu       ****\n")
                .append("*********************************************\n")
                .append("****1.       List Books                  ****\n")
                .append("****2.       CheckOut Books              ****\n")
                .append("****0.       Quit                        ****\n")
                .append("*********************************************\n")
                .append("please input what your choose(0-2):\n");
        assertEquals(message.getUserInput(),"Invalid input, Please try again\n\n"+st.toString());
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
        RouterMessage message = router.GetRouterMessage("2");

        assertTrue(message.isWaitForInput());
    }

    @Test
    public void given_a_book_list_containing_checked_out_book_When_user_select_list_books_when_current_state_is_main_menu_then_books_should_be_displayed_except_for_those_checked_out_ones() throws NoSuchProviderException {
        BibliotecaService bibliotecaService = new BibliotecaService();
        bibliotecaService.CheckoutBooks("math");

        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.GetRouterMessage("1");

        assertTrue(message.getUserInput().contains("****          All Book Detials           ****\n" +
                "*********************************************\n****    Name   PublishedYear  Author     ****\n" +
                "*********************************************\n****    chinese  huawu  2011-09-14\n****    english " +
                " danhu  2015-05-10\n"));
    }

    @Test
    public void should_display_successful_message_if_the_book_has_not_been_checked_out_and_the_book_exists_when_user_input_check_out_book_name_and_continue_execution() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Checkout, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage("math");

        assertTrue(message.getUserInput().contains("Thank you! Enjoy the book!\n\n"));
    }

    @Test
    public void given_current_state_is_checkout_when_user_input_valid_check_out_book_name_and_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Checkout, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage("math");

        StringBuilder st = new StringBuilder();
        st.append("****         This is our Main Menu       ****\n")
                .append("*********************************************\n")
                .append("****1.       List Books                  ****\n")
                .append("****2.       CheckOut Books              ****\n")
                .append("****0.       Quit                        ****\n")
                .append("*********************************************\n")
                .append("please input what your choose(0-2):\n");
        assertTrue(message.getUserInput().contains(st.toString()));
    }

    @Test
    public void given_current_state_is_checkout_should_display_unsuccessful_message_when_user_select_a_checked_book_or_the_book_does_not_exist() throws NoSuchProviderException {
        BibliotecaService bibliotecaService = new BibliotecaService();
        bibliotecaService.CheckoutBooks("math");

        BibliotecaRouter router = new BibliotecaRouter(RouterState.Checkout, bibliotecaService);
        RouterMessage message = router.GetRouterMessage("invalid input");

        assertTrue(message.getUserInput().contains("That book is not available.\n\n"));
    }

    @Test
    public void given_current_state_is_checkout_when_user_input_an_invalid_book_and_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Checkout, new BibliotecaService());
        RouterMessage message = router.GetRouterMessage("invalid input");

        StringBuilder st = new StringBuilder();
        st.append("****         This is our Main Menu       ****\n")
                .append("*********************************************\n")
                .append("****1.       List Books                  ****\n")
                .append("****2.       CheckOut Books              ****\n")
                .append("****0.       Quit                        ****\n")
                .append("*********************************************\n")
                .append("please input what your choose(0-2):\n");
        assertTrue(message.getUserInput().contains(st.toString()));
    }
}
