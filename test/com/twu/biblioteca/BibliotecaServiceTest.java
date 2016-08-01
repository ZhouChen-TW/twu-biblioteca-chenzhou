package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.service.BibliotecaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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
        List<Book> books = bibliotecaService.ListBooks();
        assertEquals("math",books.get(0).getName());
        assertEquals("chinese",books.get(1).getName());
        assertEquals("english",books.get(2).getName());
    }

    @Test
    public void should_list_all_books_names_author_and_published_year(){
        List<Book> books = bibliotecaService.ListBooks();
        assertEquals("math",books.get(0).getName());
        assertEquals("chinese",books.get(1).getName());
        assertEquals("english",books.get(2).getName());
        assertEquals("yangliu",books.get(0).getAuthor());
        assertEquals("huawu",books.get(1).getAuthor());
        assertEquals("danhu",books.get(2).getAuthor());
        assertEquals("2013-10-10",books.get(0).getPublishedYear());
        assertEquals("2011-09-14",books.get(1).getPublishedYear());
        assertEquals("2015-05-10",books.get(2).getPublishedYear());
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
        List<Book> books = bibliotecaService.ListBooks();
        assertEquals("chinese",books.get(0).getName());
        assertEquals("english",books.get(1).getName());
        assertEquals("huawu",books.get(0).getAuthor());
        assertEquals("danhu",books.get(1).getAuthor());
        assertEquals("2011-09-14",books.get(0).getPublishedYear());
        assertEquals("2015-05-10",books.get(1).getPublishedYear());
    }

    @Test
    public void should_return_true_when_call_returnBook_if_the_book_has_been_checked_out_and_the_book_exists(){
        bibliotecaService.CheckoutBooks("math");
        assertTrue(bibliotecaService.ReturnBooks("math"));
    }

    @Test
    public void should_return_false_when_call_returnBook_if_the_book_has_not_been_checked_out_or_the_book_dose_not_exist(){
        assertFalse(bibliotecaService.ReturnBooks("invaild name"));
        assertFalse(bibliotecaService.ReturnBooks("english"));
    }

    @Test
    public void should_display_books_that_are_return_when_calling_list_books(){
        bibliotecaService.CheckoutBooks("math");
        List<Book> books = bibliotecaService.ListBooks();
        assertEquals("chinese",books.get(0).getName());
        assertEquals("english",books.get(1).getName());
        assertEquals("huawu",books.get(0).getAuthor());
        assertEquals("danhu",books.get(1).getAuthor());
        assertEquals("2011-09-14",books.get(0).getPublishedYear());
        assertEquals("2015-05-10",books.get(1).getPublishedYear());

        bibliotecaService.ReturnBooks("math");
        List<Book> booksReturn = bibliotecaService.ListBooks();
        assertEquals("math",booksReturn.get(0).getName());
        assertEquals("chinese",booksReturn.get(1).getName());
        assertEquals("english",booksReturn.get(2).getName());
        assertEquals("yangliu",booksReturn.get(0).getAuthor());
        assertEquals("huawu",booksReturn.get(1).getAuthor());
        assertEquals("danhu",booksReturn.get(2).getAuthor());
        assertEquals("2013-10-10",booksReturn.get(0).getPublishedYear());
        assertEquals("2011-09-14",booksReturn.get(1).getPublishedYear());
        assertEquals("2015-05-10",booksReturn.get(2).getPublishedYear());
    }
}
