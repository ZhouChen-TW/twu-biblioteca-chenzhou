package com.twu.biblioteca;

import com.twu.biblioteca.service.BibliotecaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaServiceTest {
    private BibliotecaService bibliotecaService;
    private ByteArrayOutputStream outContent ;

    @Before
    public void setUp() throws Exception {
        bibliotecaService = new BibliotecaService();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void setDown() throws Exception{
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void Should_get_welcome_message_when_calling_getWelcomeMessage_method(){
        assertEquals(bibliotecaService.GetWelcomeMessage(),"Welcome To Biblioteca Library!\n");
    }

    @Test
    public void should_list_all_books_names_when_calling_listBooks_method(){
        bibliotecaService.ListBooks();
        assertTrue(outContent.toString().contains("****          All Book Detials           ****\n" +
                "*********************************************\n****    Name                             ****\n" +
                "*********************************************\n****    math\n****    chinese\n****    english\n"));
    }

    @Test
    public void should_list_all_books_names_author_and_published_year(){
        bibliotecaService.ListBooks();
        assertTrue(outContent.toString().contains("****          All Book Detials           ****\n" +
                "*********************************************\n****    Name   PublishedYear  Author     ****\n" +
                "*********************************************\n****    math  yangliu  2013-10-10\n" +
                "****    chinese  huawu  2011-09-14\n****    english  danhu  2015-05-10\n"));
    }

    @Test
    public void should_return_true_when_call_checkoutBook_if_the_book_has_not_been_checked_out_and_the_book_exists(){
        assertTrue(bibliotecaService.CheckoutBooks("math"));
    }

    @Test
    public void should_return_false_when_call_checkoutBook_if_the_book_has_been_checked_out_or_the_book_does_not_exist(){
        assertFalse(bibliotecaService.CheckoutBooks("invalid name"));
    }

    @Test
    public void Should_display_books_that_are_not_checked_out_when_calling_listBooks(){
        bibliotecaService.CheckoutBooks("math");
        assertEquals(bibliotecaService.ListBooks(),"****          All Book Detials           ****\n" +
                "*********************************************\n****    Name   PublishedYear  Author     ****\n" +
                "*********************************************\n****    chinese  huawu  2011-09-14\n****    english " +
                " danhu  2015-05-10\n");
    }

}
