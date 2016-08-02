package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import com.twu.biblioteca.service.BibliotecaService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BibliotecaServiceTest {
    private BibliotecaService bibliotecaService;

    @Before
    public void setUp() throws Exception {
        bibliotecaService = new BibliotecaService();
    }

    @Test
    public void Should_get_welcome_message_when_calling_getWelcomeMessage_method(){
        assertEquals(bibliotecaService.getWelcomeMessage(),"Welcome To Biblioteca Library!\n");
    }

    @Test
    public void should_list_all_books_names_when_calling_listBooks_method(){
        List<Book> books = bibliotecaService.listBooks();
        assertEquals("math",books.get(0).getName());
        assertEquals("chinese",books.get(1).getName());
        assertEquals("english",books.get(2).getName());
    }

    @Test
    public void should_list_all_books_names_author_and_published_year(){
        List<Book> books = bibliotecaService.listBooks();
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
        assertTrue(bibliotecaService.checkoutBooks("math"));
    }

    @Test
    public void should_return_false_when_call_checkoutBook_if_the_book_has_been_checked_out_or_the_book_does_not_exist(){
        assertFalse(bibliotecaService.checkoutBooks("invalid name"));
    }

    @Test
    public void Should_display_books_that_are_not_checked_out_when_calling_listBooks(){
        bibliotecaService.checkoutBooks("math");
        List<Book> books = bibliotecaService.listBooks();
        assertEquals("chinese",books.get(0).getName());
        assertEquals("english",books.get(1).getName());
        assertEquals("huawu",books.get(0).getAuthor());
        assertEquals("danhu",books.get(1).getAuthor());
        assertEquals("2011-09-14",books.get(0).getPublishedYear());
        assertEquals("2015-05-10",books.get(1).getPublishedYear());
    }

    @Test
    public void should_return_true_when_call_returnBook_if_the_book_has_been_checked_out_and_the_book_exists(){
        bibliotecaService.checkoutBooks("math");
        assertTrue(bibliotecaService.returnBooks("math"));
    }

    @Test
    public void should_return_false_when_call_returnBook_if_the_book_has_not_been_checked_out_or_the_book_dose_not_exist(){
        assertFalse(bibliotecaService.returnBooks("invaild name"));
        assertFalse(bibliotecaService.returnBooks("english"));
    }

    @Test
    public void should_display_books_that_are_return_when_calling_list_books(){
        bibliotecaService.checkoutBooks("math");
        List<Book> books = bibliotecaService.listBooks();
        assertEquals("chinese",books.get(0).getName());
        assertEquals("english",books.get(1).getName());
        assertEquals("huawu",books.get(0).getAuthor());
        assertEquals("danhu",books.get(1).getAuthor());
        assertEquals("2011-09-14",books.get(0).getPublishedYear());
        assertEquals("2015-05-10",books.get(1).getPublishedYear());

        bibliotecaService.returnBooks("math");
        List<Book> booksReturn = bibliotecaService.listBooks();
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

    @Test
    public void should_list_all_movies_with_name_year_director_and_movie_rating_when_calling_list_movies(){
        List<Movie> movies = bibliotecaService.listMoves();
        assertEquals("name0",movies.get(0).getName());
        assertEquals("1990",movies.get(0).getYear());
        assertEquals("director0",movies.get(0).getDirector());
        assertEquals("unrated",movies.get(0).getMovieRating());

        assertEquals("name1",movies.get(1).getName());
        assertEquals("1991",movies.get(1).getYear());
        assertEquals("director1",movies.get(1).getDirector());
        assertEquals("1",movies.get(1).getMovieRating());

        assertEquals("name2",movies.get(2).getName());
        assertEquals("1992",movies.get(2).getYear());
        assertEquals("director2",movies.get(2).getDirector());
        assertEquals("2",movies.get(2).getMovieRating());
    }

    @Test
    public void should_return_true_when_calling_chekout_movies_if_the_movie_exist(){
        assertTrue(bibliotecaService.checkoutMovies("name0"));
    }

    @Test
    public void should_return_false_when_calling_chekout_movies_if_the_movie_does_not_exist(){
        assertFalse(bibliotecaService.checkoutMovies("invalid name"));
    }

    @Test
    public void should_return_true_when_calling_checkout_login_if_user_input_valid_library_number_and_password(){

        assertTrue(bibliotecaService.checkoutLogin("100-0001,000000"));
    }

    @Test
    public void should_return_false_when_calling_checkout_login_if_user_input_invaild_format_library_number(){
        assertFalse(bibliotecaService.checkoutLogin("invalid ,000000"));
    }

    @Test
    public void should_return_false_when_calling_checkout_login_if_user_input_valid_format_library_number_but_invalid_password(){
        assertFalse(bibliotecaService.checkoutLogin("100-0001,000001"));
    }

    @Test
    public void should_return_true_when_calling_retrun_books_if_user_input_valid_book_and_the_book_borrow_user_is_that_user(){
        User user = new User("100-0001","000000",true);
        List<Book> books = bibliotecaService.listBooks();
        bibliotecaService.checkoutBooks("math");
        books.get(0).setUserLibraryNumber("100-0001");
        bibliotecaService.setUser(user);
        assertTrue(bibliotecaService.returnBooks("math"));
    }
}
