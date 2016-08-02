package com.twu.biblioteca;

import com.twu.biblioteca.model.RouterState;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.router.BibliotecaRouter;
import com.twu.biblioteca.router.RouterMessage;
import com.twu.biblioteca.service.BibliotecaService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchProviderException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BibliotecaShellTest {
    private BibliotecaService bibliotecaService;

    @Before
    public void setUp() throws Exception {
        bibliotecaService = new BibliotecaService();
    }

    private static String getMainMenu(){
        return "****         This is our Main Menu       ****\n" +
                "*********************************************\n" +
                "****1.       List Books                  ****\n" +
                "****2.       CheckOut Books              ****\n" +
                "****3.       Return Books                ****\n" +
                "****4.       List Movies                 ****\n" +
                "****5.       CheckOut Movies             ****\n" +
                "****6.       Login                       ****\n" +
                "****0.       Quit                        ****\n" +
                "*********************************************\n" +
                "please input what your choose:\n";
    }

    private static String getMainMenuNew(){
        return "****         This is our Main Menu       ****\n" +
                "*********************************************\n" +
                "****1.       List Books                  ****\n" +
                "****2.       CheckOut Books              ****\n" +
                "****3.       Return Books                ****\n" +
                "****4.       List Movies                 ****\n" +
                "****5.       CheckOut Movies             ****\n" +
                "****6.       Login                       ****\n" +
                "****7.       User Information            ****\n" +
                "****0.       Quit                        ****\n" +
                "*********************************************\n" +
                "please input what your choose:\n";
    }

    @Test
    public void should_display_welcome_message_when_current_state_is_initializing() throws IOException, NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Initializing, bibliotecaService);
        RouterMessage message = router.getRouterMessage("");
        assertTrue(message.getUserInput().contains("Welcome To Biblioteca Library!"));
    }

    @Test
    public void should_display_main_menu_when_current_state_is_main_menu() throws IOException, NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage(null);
        assertTrue(message.getUserInput().contains(getMainMenu()));
    }

    @Test
    public void should_display_book_list_when_current_state_is_main_menu_and_user_input_is_list_books() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("1");

        assertEquals(message.getUserInput(),"****          All Book Detials           ****\n" +
                "*********************************************\n****    Name   PublishedYear  Author     ****\n" +
                "*********************************************\n****    math  yangliu  2013-10-10\n" +
                "****    chinese  huawu  2011-09-14\n****    english  danhu  2015-05-10\n");
    }

    @Test
    public void should_display_an_invalid_message_when_user_input_is_not_list_books_and_current_state_is_main_menu() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("invalid input");

        assertEquals(message.getUserInput(),"Select a valid option!\n\n");
    }

    @Test
    public void should_display_main_menu_when_user_input_is_not_list_books_and_current_state_is_main_menu_and_continue_execution() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        router.getRouterMessage("invalid input");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(), getMainMenu());
    }

    @Test
    public void should_quit_when_user_input_is_quit_and_current_state_is_main_menu() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("0");

        assertTrue(message.isExit());
        assertFalse(message.isWaitForInput());
    }

    @Test
    public void should_waiting_for_user_input_when_user_select_checkout_books_and_current_status_is_main_menu() throws NoSuchProviderException {
        User userLogin = new User(true);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("2");

        assertTrue(message.isWaitForInput());
    }

    @Test
    public void given_a_book_list_containing_checked_out_book_When_user_select_list_books_when_current_state_is_main_menu_then_books_should_be_displayed_except_for_those_checked_out_ones() throws NoSuchProviderException {
        User userLogin = new User(true);
        bibliotecaService.setUser(userLogin);
        bibliotecaService.checkoutBooks("math");

        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("1");

        assertTrue(message.getUserInput().contains("****          All Book Detials           ****\n" +
                "*********************************************\n****    Name   PublishedYear  Author     ****\n" +
                "*********************************************\n****    chinese  huawu  2011-09-14\n****    english " +
                " danhu  2015-05-10\n"));
    }

    @Test
    public void should_display_successful_message_if_the_book_has_not_been_checked_out_and_the_book_exists_when_user_input_check_out_book_name_and_continue_execution() throws NoSuchProviderException {
        User userLogin = new User(true);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.CheckoutBooks, bibliotecaService);
        RouterMessage message = router.getRouterMessage("math");

        assertTrue(message.getUserInput().contains("Thank you! Enjoy the book!\n\n"));
    }

    @Test
    public void given_current_state_is_checkout_when_user_input_valid_check_out_book_name_and_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.CheckoutBooks, bibliotecaService);
        router.getRouterMessage("math");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),getMainMenu());
    }

    @Test
    public void given_current_state_is_checkout_should_display_unsuccessful_message_when_user_select_a_checked_book_or_the_book_does_not_exist() throws NoSuchProviderException {
        bibliotecaService.checkoutBooks("math");

        BibliotecaRouter router = new BibliotecaRouter(RouterState.CheckoutBooks, bibliotecaService);
        RouterMessage message = router.getRouterMessage("invalid input");

        assertTrue(message.getUserInput().contains("That book is not available.\n\n"));
    }

    @Test
    public void given_current_state_is_checkout_when_user_input_an_invalid_book_and_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.CheckoutBooks, bibliotecaService);
        router.getRouterMessage("invalid input");
        RouterMessage message = router.getRouterMessage(null);

        assertTrue(message.getUserInput().contains(getMainMenu()));
    }

    @Test
    public void should_waiting_for_user_input_when_user_select_return_books_and_current_status_is_main_menu() throws NoSuchProviderException {
        User userLogin = new User(true);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("3");

        assertTrue(message.isWaitForInput());
    }

    @Test
    public void given_a_book_list_containing_return_books_when_user_select_list_books_when_current_state_is_main_menu_then_books_should_be_displayed_contains_those_returned_ones() throws NoSuchProviderException {
        User userLogin = new User("100-0001","000000",true);
        bibliotecaService.setUser(userLogin);
        bibliotecaService.checkoutBooks("math");
        bibliotecaService.checkoutBooks("english");
        bibliotecaService.returnBooks("english");
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("1");

        assertEquals(message.getUserInput(),"****          All Book Detials           ****\n" +
                "*********************************************\n****    Name   PublishedYear  Author     ****\n" +
                "*********************************************\n****    chinese  huawu  2011-09-14\n****    english " +
                " danhu  2015-05-10\n");
    }

    @Test
    public void should_display_successful_message_when_user_return_a_valid_book_and_continue_execution() throws NoSuchProviderException {
        User userLogin = new User("100-0001","000000",true);
        bibliotecaService.setUser(userLogin);
        bibliotecaService.checkoutBooks("math");
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Return, bibliotecaService);
        RouterMessage message = router.getRouterMessage("math");

        assertTrue(message.getUserInput().contains("Thank you for returning the book.\n\n"));
    }

    @Test
    public void given_current_state_is_returnBook_when_user_input_a_valid_return_book_name_and_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        bibliotecaService.checkoutBooks("math");
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Return, bibliotecaService);
        router.getRouterMessage("math");
        RouterMessage message = router.getRouterMessage(null);

        assertTrue(message.getUserInput().contains(getMainMenu()));
    }

    @Test
    public void given_current_state_is_return_book_should_display_unsuccessful_message_when_user_return_an_unchecked_book_or_the_book_does_not_exist() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Return, bibliotecaService);
        RouterMessage message = router.getRouterMessage("invaild names");

        assertTrue(message.getUserInput().contains("That is not a valid book to return.\n\n"));
    }

    @Test
    public void given_current_state_is_returnBook_when_user_input_an_invalid_return_book_name_and_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        bibliotecaService.checkoutBooks("math");
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Return, bibliotecaService);
        router.getRouterMessage("invaild name");
        RouterMessage message = router.getRouterMessage(null);

        assertTrue(message.getUserInput().contains(getMainMenu()));
    }

    @Test
    public void should_display_all_available_movies_when_user_select_list_movies_and_current_state_is_main_menu() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        RouterMessage message = router.getRouterMessage("4");

        assertEquals(message.getUserInput(),"****          All Movie Detials          ****\n********" +
                "*************************************\n****    Name  Year  Director  Rating     ****\n" +
                "*********************************************\n****    name0  1990  director0  unrated\n" +
                "****    name1  1991  director1  1\n****    name2  1992  director2  2\n");
    }

    @Test
    public void given_user_input_list_movies_in_main_menu_state_when_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu, bibliotecaService);
        router.getRouterMessage("4");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),getMainMenu());
    }

    @Test
    public void should_waiting_for_user_input_when_user_select_checkout_movies_and_current_status_is_main_menu() throws NoSuchProviderException {
        User userLogin = new User(true);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu,bibliotecaService);
        RouterMessage message = router.getRouterMessage("5");

        assertTrue(message.isWaitForInput());
    }

    @Test
    public void given_user_input_in_checkout_movies_state_when_continue_execution_then_main_menu_should_be_displayed() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.CheckoutMovies,bibliotecaService);
        router.getRouterMessage("5");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),getMainMenu());
    }

    @Test
    public void should_display_main_menu_when_current_state_is_login_and_continue_execution() throws NoSuchProviderException {
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Login,bibliotecaService);
        router.getRouterMessage("100-0001,000000");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),getMainMenu());
    }

    @Test
    public void should_dispaly_login_and_waiting_for_user_input_when_main_menu_user_select_checkout_books_and_current_user_not_login_if_continue_execution() throws NoSuchProviderException {
        User userLogin = new User(false);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu,bibliotecaService);
        router.getRouterMessage("2");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),"please input your message with this format (library number,password)");
    }

    @Test
    public void should_waiting_for_user_input_when_user_select_login_and_current_state_is_main_menu() throws NoSuchProviderException {
        User userLogin = new User(false);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu,bibliotecaService);
        RouterMessage message = router.getRouterMessage("6");

        assertTrue(message.isWaitForInput());
    }

    @Test
    public void should_dispaly_login_and_waiting_for_user_input_when_main_menu_user_select_checkout_movies_and_current_user_not_login_if_continue_execution() throws NoSuchProviderException {
        User userLogin = new User(false);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu,bibliotecaService);
        router.getRouterMessage("5");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),"please input your message with this format (library number,password)");
    }

    @Test
    public void should_dispaly_login_waiting_for_user_input_when_main_menu_user_select_return_books_and_current_user_not_login_if_continue_execution() throws NoSuchProviderException {
        User userLogin = new User(false);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu,bibliotecaService);
        router.getRouterMessage("3");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),"please input your message with this format (library number,password)");
    }

    @Test
    public void should_display_main_menu_contains_user_information_when_current_state_is_login_and_user_login_success_and_contine_execution() throws NoSuchProviderException {
        User userLogin = new User(false);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Login,bibliotecaService);
        router.getRouterMessage("100-0001,000000");
        RouterMessage message = router.getRouterMessage("new");

        assertEquals(message.getUserInput(),getMainMenuNew());
    }

    @Test
    public void should_display_main_menu_not_contains_user_information_when_current_state_is_login_and_user_login_unsuccess_and_contine_execution() throws NoSuchProviderException {
        User userLogin = new User(false);
        bibliotecaService.setUser(userLogin);
        BibliotecaRouter router = new BibliotecaRouter(RouterState.Login,bibliotecaService);
        router.getRouterMessage("100-0001,000001");
        RouterMessage message = router.getRouterMessage(null);

        assertEquals(message.getUserInput(),getMainMenu());
    }

    @Test
    public void should_display_current_user_information_when_user_select_user_information_when_current_state_is_main_menu() throws NoSuchProviderException {
        User userLogin = new User(true);
        bibliotecaService.setUser(userLogin);
        bibliotecaService.checkoutLogin("100-0001,000000");
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu,bibliotecaService);
        RouterMessage message = router.getRouterMessage("7");

        assertEquals(message.getUserInput(),"****    name: name0  email address: email0  phone number: phone0");
    }

    @Test
    public void should_display_main_menu_if_user_select_user_information_and_continue_exection_when_current_state_is_main_menu() throws NoSuchProviderException {
        User userLogin = new User(true);
        bibliotecaService.setUser(userLogin);
        bibliotecaService.checkoutLogin("100-0001,000000");
        BibliotecaRouter router = new BibliotecaRouter(RouterState.MainMenu,bibliotecaService);
        router.getRouterMessage("7");
        RouterMessage message = router.getRouterMessage("new");

        assertEquals(message.getUserInput(),getMainMenuNew());
    }
}
