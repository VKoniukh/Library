package ua.koniukh.service;

import ua.koniukh.model.Book;
import ua.koniukh.model.dto.BookDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();

    List<BookDTO> getCheckedOutBooks();

    List<BookDTO> bookToBookDtoList(List<Book> books);

    BookDTO bookToDto(Book book);

    Boolean isBookPresentByName(String name);

    void save(BookDTO bookDTO);

    Date LocalDateToDate(LocalDate ld);
}
