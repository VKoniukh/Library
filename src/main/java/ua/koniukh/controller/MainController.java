package ua.koniukh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.koniukh.model.dto.BookDTO;
import ua.koniukh.service.impl.BookServiceImpl;

import javax.validation.Valid;

@Controller
public class MainController {

    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public MainController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("allBooks", bookServiceImpl.getAllBooks());
        model.addAttribute("checkedOutBooks", bookServiceImpl.getCheckedOutBooks());
        return "indexCallsApi";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") BookDTO bookDTO) {
        return "/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("book") @Valid BookDTO bookDTO,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/new";

        bookServiceImpl.save(bookDTO);
        return "redirect:/index";
    }
}
