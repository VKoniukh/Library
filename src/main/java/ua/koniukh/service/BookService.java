package ua.koniukh.service;

import ua.koniukh.model.Book;
import ua.koniukh.model.dto.BookDTO;

import java.util.List;

public interface BookService {
    public List<BookDTO> getAllBooks();

    public List<BookDTO> getCheckedOutBooks(Boolean bol);

    public List<BookDTO> bookToBookDtoList(List<Book> books);

    public BookDTO bookToDto(Book book);
}
