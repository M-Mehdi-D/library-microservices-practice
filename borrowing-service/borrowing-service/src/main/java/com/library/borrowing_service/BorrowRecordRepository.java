package com.library.borrowing_service;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
    Optional<BorrowRecord> findByIdempotencyKey(String idempotencyKey);
}
