package by.galdovich.task_6.model.dao.impl;

import by.galdovich.task_6.creator.ConnectionCreator;
import by.galdovich.task_6.model.dao.BookListDao;
import by.galdovich.task_6.model.dao.BookTableName;
import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class BookListDaoImpl implements BookListDao {
    private static final BookListDaoImpl INSTANCE = new BookListDaoImpl();
    private static final String SQL_ADD_BOOK = "INSERT INTO books(title, year, pages, isbn, authors) " +
            "VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_REMOVE_BOOK = "DELETE FROM books WHERE title = ? AND year = ? AND pages = ? " +
            "AND isbn = ? AND authors = ?";
    private static final String SQL_FIND_BY_ID = "SELECT title, year, pages, isbn, authors FROM books " +
            "WHERE id = ?";
    private static final String SQL_FIND_BY_TITLE = "SELECT title, year, pages, isbn, authors FROM books " +
            "WHERE title = ?";
    private static final String SQL_FIND_BY_YEAR = "SELECT title, year, pages, isbn, authors FROM books " +
            "WHERE year = ?";
    private static final String SQL_FIND_BY_PAGES = "SELECT title, year, pages, isbn, authors FROM books " +
            "WHERE pages = ?";
    private static final String SQL_FIND_BY_ISBN = "SELECT title, year, pages, isbn, authors FROM books " +
            "WHERE isbn = ?";
    private static final String SQL_FIND_BY_AUTHORS = "SELECT title, year, pages, isbn, authors FROM books " +
            "WHERE authors = ?";
    private static final String SQL_SORT = "SELECT title, year, pages, isbn, authors FROM books ORDER BY " ;

    private BookListDaoImpl() {
    }

    public static BookListDaoImpl getInstance() {
        return INSTANCE;
    }

    public List<Book> findAll(String sortTag) throws DaoException{
        List<Book> resultList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(SQL_SORT);
        stringBuilder.append(sortTag);
        String sqlQuery = stringBuilder.toString();
        System.out.println();
        try(Connection connection = ConnectionCreator.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Book book = createBook(resultSet);
                resultList.add(book);
            }
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
        return resultList;
    }

    public boolean addBook(Book book) throws DaoException {
        boolean result = false;
        try(Connection connection = ConnectionCreator.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_BOOK)){
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getYear());
            statement.setInt(3, book.getPages());
            statement.setString(4, book.getIsbn());
            statement.setString(5, book.getAuthor().toString());
            result = statement.execute();
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
        return result;
    }

    public boolean removeBook(Book book) throws DaoException {
        try(Connection connection = ConnectionCreator.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_BOOK)){
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getYear());
            statement.setInt(3, book.getPages());
            statement.setString(4, book.getIsbn());
            statement.setString(5, book.getAuthor().toString());
            return statement.execute();
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
    }

    public List<Book> findByTitle(String inputTitle) throws DaoException{
        List<Book> resultList = new ArrayList<>();
        try(Connection connection = ConnectionCreator.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_TITLE)){
            statement.setString(1, inputTitle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Book book = createBook(resultSet);
                resultList.add(book);
            }
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
        return resultList;
    }

    public List<Book> findByYear(int inputYear) throws DaoException{
        List<Book> resultList = new ArrayList<>();
        try(Connection connection = ConnectionCreator.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_YEAR)){
            statement.setInt(1, inputYear);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Book book = createBook(resultSet);
                resultList.add(book);
            }
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
        return resultList;
    }

    public List<Book> findByPages(int pages) throws DaoException{
        List<Book> resultList = new ArrayList<>();
        try(Connection connection = ConnectionCreator.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_PAGES)){
            statement.setInt(1, pages);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Book book = createBook(resultSet);
                resultList.add(book);
            }
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
        return resultList;
    }

    public List<Book> findByIsbn(String isbn) throws DaoException {
        List<Book> resultList = new ArrayList<>();
        try(Connection connection = ConnectionCreator.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ISBN)){
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Book book = createBook(resultSet);
                resultList.add(book);
            }
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
        return resultList;
    }

    public List<Book> findByAuthors(String authors) throws DaoException {
        List<Book> resultList = new ArrayList<>();
        try(Connection connection = ConnectionCreator.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_AUTHORS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Book book = createBook(resultSet);
                resultList.add(book);
            }
        }catch (SQLException exc){
            throw new DaoException(exc);
        }
        return resultList;
    }

    private Book createBook(ResultSet resultSet) throws SQLException{
        Book book;
        String title = resultSet.getString(BookTableName.TITLE_COLUMN);
        int year = resultSet.getInt(BookTableName.YEAR_COLOMN);
        int pages = resultSet.getInt(BookTableName.PAGES_COLUMN);
        String isbn = resultSet.getString(BookTableName.ISBN_COLUMN);
        String author = resultSet.getString(BookTableName.AUTHOR_COLUMN);
        book = new Book(title, year, pages, isbn, author);
        return book;
    }
}
