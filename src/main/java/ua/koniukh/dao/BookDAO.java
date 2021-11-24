package ua.koniukh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.koniukh.model.Book;

import java.util.Date;
import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query(
                "SELECT * FROM Book",
                (rs, rowNum) -> {
                    Book book = new Book();
                    book.setName(rs.getString(1));
                    book.setDateAdded(rs.getDate(2));
                    book.setAvailable(rs.getBoolean(3));
                    return book;
                });
    }

    public List<Book> getCheckedOutBooks(Boolean bol) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE available=?",
                (rs, rowNum) -> {
                    Book book = new Book();
                    book.setName(rs.getString(1));
                    book.setDateAdded(rs.getDate(2));
                    book.setAvailable(rs.getBoolean(3));
                    return book;
                }, bol);
    }

    public Boolean isBookPresentByName(String name) {
        List<Book> books = jdbcTemplate.query(
                "SELECT * FROM Book WHERE name=? LIMIT 1",
                (rs, rowNum) -> {
                    Book book = new Book();
                    book.setName(rs.getString(1));
                    book.setDateAdded(rs.getDate(2));
                    book.setAvailable(rs.getBoolean(3));
                    return book;
                }, name);

        return books.isEmpty();
    }

    public void saveBook(String name, Date dateAdded, Boolean available) {
        jdbcTemplate.update("INSERT INTO Book VALUES (?, ?, ?)", name, dateAdded, available);
    }
}
