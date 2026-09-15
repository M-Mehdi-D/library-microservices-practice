package com.library.borrowing_service;

import java.util.Date;
import java.util.Optional;

import com.library.borrowing_service.dto.BookDto;
import com.library.borrowing_service.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import com.library.borrowing_service.client.MemberClient;
import com.library.borrowing_service.client.BookClient;

@Service
public class BorrowService {
    @Autowired
    private BorrowRecordRepository borrowRepo;
    @Autowired
    private MemberClient memberClient;
    @Autowired
    private BookClient bookClient;

    // This method uses a circuit breaker named "borrowBreaker"
    @CircuitBreaker(name = "borrowBreaker", fallbackMethod = "borrowFallback")
    public String borrowBook(int memberId, int bookId, String idempotencyKey) {
        // Step A: if a key was provided and was seen before return the old result
        if (idempotencyKey !=null) {
             Optional<BorrowRecord> existing = borrowRepo.findByIdempotencyKey(idempotencyKey);
             if (existing.isPresent()) {
                 return "Already processed. Due date: " + existing.get().getDueDate();
             }
        }

        // Step B: otherwise do the normal procedure
        // 1.Call Member Service to check if member exists
        MemberDto member = memberClient.getMember(memberId);
        if(member == null) return "Member not found.";

        // 2.Call Book Service to check if book exists and is available
        BookDto book = bookClient.getBook(bookId);
        if (book == null) return "Book not found.";
        if (!book.isAvailable()) return "Book is already borrowed.";

        // 3.Create the Borrow record
        long sevenDaysInMilliSeconds = 7L * 24 * 60 * 60 * 1000;
        BorrowRecord record = new BorrowRecord();
        record.setMemberId(memberId);
        record.setBookId(bookId);
        record.setDueDate(new Date(System.currentTimeMillis() + sevenDaysInMilliSeconds));
        record.setIdempotencyKey(idempotencyKey);
        borrowRepo.save(record);

        // 4. Tell book Service to mark it as unavailable
        bookClient.setAvailability(bookId, false);

        return "Book borrowed successfully! Due date: " + record.getDueDate();
        }

        // Fallback method if teh circuit breaker trips
        public String borrowFallback(int memberId, int bookId, String idempotencyKey, Throwable t) {
        t.printStackTrace(); // ****TEMPORARY for debugging
          return "Fallback: " + t.getClass().getSimpleName() + ": " + t.getMessage();
        //return "Service temporarily unavailable. Please try again later. (Circuit Breaker tripped)";
    }
}
