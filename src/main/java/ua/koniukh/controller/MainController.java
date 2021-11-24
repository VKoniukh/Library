package ua.koniukh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.koniukh.model.dto.BookDTO;
import ua.koniukh.service.impl.BookServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/books/")
public class MainController {

    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public MainController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDtoList = this.bookServiceImpl.getAllBooks();

        if (bookDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }


}


