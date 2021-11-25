package ua.koniukh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.koniukh.dao.BookDAO;
import ua.koniukh.model.Book;
import ua.koniukh.model.dto.BookDTO;
import ua.koniukh.service.BookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookToBookDtoList(bookDAO.getAllBooks());
    }

    @Override
    public List<BookDTO> getCheckedOutBooks() {
        return bookToBookDtoList(bookDAO.getCheckedOutBooks(false));
    }

    @Override
    public BookDTO bookToDto(Book book) {
        return BookDTO.builder()
                .name(book.getName())
                .dateAdded(book.getDateAdded().toString())
                .available(book.isAvailable())
                .build();
    }

    @Override
    public List<BookDTO> bookToBookDtoList(List<Book> books) {
        List<Book> allBookList = new ArrayList<>(books);
        List<BookDTO> allBookDtoList = new ArrayList<>();
        for (Book book : allBookList) {
            allBookDtoList.add(bookToDto(book));
        }
        return allBookDtoList;
    }

    @Override
    public Boolean isBookPresentByName(String name) {
        return !bookDAO.isBookPresentByName(name);
    }

    @Override
    public void save(BookDTO bookDTO) {
        Date dateAdded = LocalDateToDate(LocalDate.now());
        bookDTO.setAvailable(true);
        bookDAO.saveBook(bookDTO.getName(), dateAdded, bookDTO.isAvailable());
    }

    @Override
    public Date LocalDateToDate(LocalDate ld) {
        return java.sql.Date.valueOf(ld);
    }
}
