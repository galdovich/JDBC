package by.galdovich.task_6.model.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private String title;
    private int year;
    private int pages;
    private String isbn;
    private List<String> authors;

    public Book() {
    }

    public Book(String title, int year, int pages, String isbn, String... authors) {
        this.title = title;
        this.year = year;
        this.pages = pages;
        this.isbn = isbn;
        this.authors = new ArrayList<>();
        this.authors.addAll(Arrays.asList(authors));
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;}
        Book book = (Book) o;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (year != book.year) return false;
        if (pages != book.pages) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        return isbn != null ? isbn.equals(book.isbn) : book.isbn == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + pages;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book: ");
        sb.append("title = '").append(title).append('\'');
        sb.append(", authors = ").append(authors);
        sb.append(", year = ").append(year);
        sb.append(", pages = ").append(pages);
        sb.append(", ISBN = '").append(isbn).append('\'');
        return sb.toString();
    }
}
