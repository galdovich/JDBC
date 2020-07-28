package by.galdovich.task_6.service;

import by.galdovich.task_6.model.dao.impl.BookListDaoImpl;
import by.galdovich.task_6.model.entity.Book;
import by.galdovich.task_6.model.exception.DaoException;
import by.galdovich.task_6.service.exception.ServiceException;
import by.galdovich.task_6.service.parser.BookParser;
import by.galdovich.task_6.service.type.TagType;
import by.galdovich.task_6.service.validator.BookValidator;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class BookService {

    public List<Book> addBook(Map<String, String> values) throws ServiceException {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> resultList = new ArrayList<>();
        BookValidator validator = new BookValidator();
        BookParser parser = new BookParser();
        if (!validator.validateAllBookTag(values)){
            throw new ServiceException("Invalid book parameters");
        }
        Book book = parser.parseBook(values);
        try {
            bookListDao.addBook(book);
            resultList.add(book);
        }catch (DaoException exc){
            throw new ServiceException(exc.getMessage());
        }
        return resultList;
    }

    public List<Book> removeBook(Map<String, String> values) throws ServiceException {
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> resultList = new ArrayList<>();
        BookParser parser = new BookParser();
        BookValidator validator = new BookValidator();
        if (!validator.validateAllBookTag(values)){
            throw new ServiceException("Invalid book parameters");
        }
        Book book = parser.parseBook(values);
        try {
            bookListDao.removeBook(book);
            resultList.add(book);
        }catch (DaoException exc){
            throw new ServiceException(exc.getMessage());
        }
        return resultList;
    }

    public List<Book> findBookByTag(Map<String, String> values) throws ServiceException, DaoException{
        List<Book> resultList = new ArrayList<>();
        BookValidator validator = new BookValidator();
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        BookParser parser = new BookParser();
        if (!validator.validateFindBookTag(values)){
            throw new ServiceException("Invalid book parameters");
        }
        TagType tag = parser.parseBookTag(values);
        switch (tag){
            case TITLE:
                resultList = bookListDao.findByTitle(values.get(tag.getTagName()));
                break;
            case YEAR:
                int year = Integer.parseInt(values.get(tag.getTagName()));
                resultList = bookListDao.findByYear(year);
                break;
            case PAGES:
                int pages = Integer.parseInt(values.get(tag.getTagName()));
                resultList = bookListDao.findByPages(pages);
                break;
            case ISBN:
                resultList = bookListDao.findByIsbn(values.get(tag.getTagName()));
                break;
            case AUTHORS:
                resultList = bookListDao.findByAuthors(values.get(tag.getTagName()));
                break;
        }
        return resultList;
    }

    public List<Book> sortBookByTag(Map<String, String> values) throws ServiceException{
        BookListDaoImpl bookListDao = BookListDaoImpl.getInstance();
        List<Book> resultList = new ArrayList<>();
        BookParser parser = new BookParser();
        TagType tag;
        try{
            tag = parser.parseBookTag(values);
        }catch (IllegalArgumentException exc){
            throw new ServiceException("Tag type exception");
        }
        try {
            switch (tag){
                case TITLE:
                    resultList = bookListDao.findAll(TagType.TITLE.getTagName());
                    break;
                case YEAR:
                    resultList = bookListDao.findAll(TagType.YEAR.getTagName());
                    break;
                case PAGES:
                    resultList = bookListDao.findAll(TagType.PAGES.getTagName());
                    break;
                case ISBN:
                    resultList = bookListDao.findAll(TagType.ISBN.getTagName());
                    break;
                case AUTHORS:
                    resultList = bookListDao.findAll(TagType.AUTHORS.getTagName());
                    break;
            }
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return resultList;
    }
}
