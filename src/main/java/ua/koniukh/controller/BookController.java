package ua.koniukh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.koniukh.model.dto.BookDTO;
import ua.koniukh.service.impl.BookServiceImpl;

import java.util.List;

@RestController
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping("/all_books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDtoList = this.bookServiceImpl.getAllBooks();

        if (bookDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @GetMapping("/checked-out_books")
    public ResponseEntity<List<BookDTO>> getCheckedOutBooks() {
        List<BookDTO> bookDtoList = this.bookServiceImpl.getCheckedOutBooks();

        if (bookDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @PostMapping("/add_book")
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {

        if (bookServiceImpl.isBookPresentByName(bookDTO.getName())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        bookServiceImpl.save(bookDTO);
        if (bookServiceImpl.isBookPresentByName(bookDTO.getName())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


