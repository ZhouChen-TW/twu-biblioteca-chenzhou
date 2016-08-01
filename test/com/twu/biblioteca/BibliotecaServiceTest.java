package com.twu.biblioteca;

import com.twu.biblioteca.service.BibliotecaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by chenzhou on 8/1/16.
 */
public class BibliotecaServiceTest {
    private BibliotecaService bibliotecaService;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        bibliotecaService = new BibliotecaService();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void setDown() throws Exception{
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void Should_get_welcome_message_when_calling_getWelcomeMessage_method(){
        bibliotecaService.GetWelcomeMessage();
        assertTrue(outContent.toString().contains("Welcome To Biblioteca Library!"));
    }

    @Test
    public void should_list_all_books_names_when_calling_listBooks_method(){
        bibliotecaService.ListBooks();
        assertTrue(outContent.toString().contains("****          All Book Detials           ****\n" +
                "*********************************************\n****    Name                             ****\n" +
                "*********************************************\n****    math\n****    chinese\n****    english\n"));
    }
    
}
