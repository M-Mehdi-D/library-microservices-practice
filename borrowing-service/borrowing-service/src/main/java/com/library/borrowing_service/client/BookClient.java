package com.library.borrowing_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.library.borrowing_service.dto.BookDto;

@FeignClient(name = "book-service")
public interface BookClient {
    @GetMapping("/books/{id}")
    BookDto getBook(@PathVariable("id") int id);

    @PutMapping("/books/{id}/availability")
    void setAvailability(@PathVariable("id") int id, @RequestParam boolean available);
}
