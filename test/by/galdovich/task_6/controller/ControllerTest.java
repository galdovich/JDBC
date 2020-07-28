package by.galdovich.task_6.controller;

import by.galdovich.task_6.controller.exception.CommandException;
import by.galdovich.task_6.model.entity.Book;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ControllerTest {
    private Controller controller = Controller.getInstance();

    @Test
    public void testDoRequestFindByYear() throws CommandException{
        Book book = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        Map<String, String> map = new HashMap<>();
        map.put("year", "2014");
        List<Book> actual = controller.processRequest("find", map);
        assertEquals(actual, expected);
    }

    @Test
    public void testDoRequestFindByPages() throws CommandException{
        Book book = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        Map<String, String> map = new HashMap<>();
        map.put("pages", "619");
        List<Book> actual = controller.processRequest("find", map);
        assertEquals(actual, expected);
    }

    @Test
    public void testDoRequestFindByTitle() throws CommandException{
        Book book = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        Map<String, String> map = new HashMap<>();
        map.put("title", "Java: A Beginner’s Guide (Sixth Edition)");
        List<Book> actual = controller.processRequest("find", map);
        assertEquals(actual, expected);
    }

    @Test
    public void testDoRequestFindByIsbn() throws CommandException{
        Book book = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        Map<String, String> map = new HashMap<>();
        map.put("isbn", "0071809252");
        List<Book> actual = controller.processRequest("find", map);
        assertEquals(actual, expected);
    }

    @Test
    public void testDoRequestFindByAuthors() throws CommandException{
        Book book = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        Map<String, String> map = new HashMap<>();
        map.put("authors", "Herbert Schildt");
        List<Book> actual = controller.processRequest("find", map);
        assertEquals(actual, expected);
    }

    @Test
    public void testDoRequestSortByTitle() throws CommandException{
        Book book1 = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        Book book2 = new Book("Harry Potter and the Philosopher's Stone", 1994,52,
                "0-7475-3269-9", "Joanne Rowling");
        Book book3 = new Book("Astrophysics for People in a Hurry", 2017,244,
                "0393609391", "Neil de Grasse Tyson");
        Book book4 = new Book("The Naked Ape: A Zoologist's Study of the Human Animal", 1969,252,
                "0385334303", "Desmond Morris");
        List<Book> expected = new ArrayList<>();
        expected.add(book3);
        expected.add(book4);
        expected.add(book2);
        expected.add(book1);
        Map<String, String> map = new HashMap<>();
        map.put("title", "");
        List<Book> actual = controller.processRequest("sort", map);
        assertEquals(actual, expected);
    }

    @Test
    public void testDoRequestSortByYear() throws CommandException{
        Book book1 = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        Book book2 = new Book("Harry Potter and the Philosopher's Stone", 1994,52,
                "0-7475-3269-9", "Joanne Rowling");
        Book book3 = new Book("Astrophysics for People in a Hurry", 2017,244,
                "0393609391", "Neil de Grasse Tyson");
        Book book4 = new Book("The Naked Ape: A Zoologist's Study of the Human Animal", 1969,252,
                "0385334303", "Desmond Morris");
        List<Book> expected = new ArrayList<>();
        expected.add(book4);
        expected.add(book2);
        expected.add(book1);
        expected.add(book3);
        Map<String, String> map = new HashMap<>();
        map.put("year", "");
        List<Book> actual = controller.processRequest("sort", map);
        assertEquals(actual, expected);
    }

    @Test
    public void testDoRequestSortByAuthors() throws CommandException{
        Book book1 = new Book("Java: A Beginner’s Guide (Sixth Edition)", 2014,619,
                "0071809252", "Herbert Schildt");
        Book book2 = new Book("Harry Potter and the Philosopher's Stone", 1994,52,
                "0-7475-3269-9", "Joanne Rowling");
        Book book3 = new Book("Astrophysics for People in a Hurry", 2017,244,
                "0393609391", "Neil de Grasse Tyson");
        Book book4 = new Book("The Naked Ape: A Zoologist's Study of the Human Animal", 1969,252,
                "0385334303", "Desmond Morris");
        List<Book> expected = new ArrayList<>();
        expected.add(book4);
        expected.add(book3);
        expected.add(book1);
        expected.add(book2);
        Map<String, String> map = new HashMap<>();
        map.put("authors", "");
        List<Book> actual = controller.processRequest("sort", map);
        assertEquals(actual, expected);
    }
}