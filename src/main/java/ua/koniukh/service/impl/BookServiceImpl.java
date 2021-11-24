package ua.koniukh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.koniukh.dao.BookDAO;
import ua.koniukh.model.Book;
import ua.koniukh.model.dto.BookDTO;
import ua.koniukh.service.BookService;

import java.util.ArrayList;
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
    public List<BookDTO> getCheckedOutBooks(Boolean bol) {
        return bookToBookDtoList(bookDAO.getCheckedOutBooks(false));
    }

    @Override
    public BookDTO bookToDto(Book book) {
        BookDTO bookDto = BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .available(book.isAvailable())
                .build();
        return bookDto;
    }

    @Override
    public List<BookDTO> bookToBookDtoList(List<Book> books){
        List<Book> allBookList = new ArrayList<>(bookDAO.getAllBooks());
        List<BookDTO> allBookDtoList = new ArrayList<>();
        for (Book book : allBookList) {
            allBookDtoList.add(bookToDto(book));
        }
        return allBookDtoList;
    }
}
