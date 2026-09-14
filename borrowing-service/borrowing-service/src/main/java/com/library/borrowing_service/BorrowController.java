package com.library.borrowing_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public String borrowBook(@RequestParam int memberId, @RequestParam int bookId) {
        return borrowService.borrowBook(memberId, bookId);
    }
}
