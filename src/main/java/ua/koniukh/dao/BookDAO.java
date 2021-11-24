package ua.koniukh.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.koniukh.model.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> getCheckedOutBooks(Boolean bol) {
        List<Book> books = jdbcTemplate.query(
                "SELECT * FROM Book WHERE available=?",
                (rs, rowNum) -> {
                    Book book = new Book();
                    book.setId(rs.getLong(1));
                    book.setName(rs.getString(2));
                    book.setAvailable(rs.getBoolean(3));
                    return book;
                }, bol);
        return books;
    }
}
