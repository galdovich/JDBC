package by.galdovich.task_6.model.dao;

import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.model.exception.DaoException;

import java.util.List;

public interface BookListDao {

    boolean addBook(Book book) throws DaoException;

    boolean removeBook(Book book) throws DaoException;

    List<Book> findAll(String sortType) throws DaoException;

    List<Book> findByTitle(String title) throws DaoException;

    List<Book> findByYear(int year) throws DaoException;

    List<Book> findByPages(int pages) throws DaoException;

    List<Book> findByIsbn(String isbn) throws DaoException;

    List<Book> findByAuthors(String authors) throws DaoException;
}
