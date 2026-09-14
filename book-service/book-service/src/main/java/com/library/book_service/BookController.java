package com.library.book_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository repository;

    @PostMapping
    public Book addBook(@RequestParam String title, @RequestParam String author, @RequestParam String genre) {
        return repository.save(new Book(title, author, genre));
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable int id) {
        return repository.findById(id);
    }

    @GetMapping("/search")
    public Optional<Book> getBookByTitle(@RequestParam String title) {
        return repository.findByTitle(title);
    }

    @PutMapping("/{id}/availability")
    public void setAvailability(@PathVariable int id, @RequestParam boolean available) {
        Book book = repository.findById(id).orElseThrow();
        book.setAvailable(available);
        repository.save(book);
    }
}
