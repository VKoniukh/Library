package ua.koniukh.service;

import ua.koniukh.model.Book;
import ua.koniukh.model.dto.BookDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookService {
    public List<BookDTO> getAllBooks();

    public List<BookDTO> getCheckedOutBooks();

    public List<BookDTO> bookToBookDtoList(List<Book> books);

    public BookDTO bookToDto(Book book);

    public Boolean isBookPresentByName(String name);

    public void save(BookDTO bookDTO);

    public Date dateSetter(LocalDate ld);
}
