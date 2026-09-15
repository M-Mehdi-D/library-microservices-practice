package com.library.borrowing_service;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrow_records", uniqueConstraints = @UniqueConstraint(columnNames = "idempotency_key"))
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int memberId;
    private int bookId;
    private Date dueDate;

    @Column(name = "idempotency_key", unique = true)
    private String idempotencyKey;

    //Getters
    public int getId() {return this.id;}
    public int getMemberId() {return this.memberId;}
    public int getBookId() {return this.bookId;}
    public Date getDueDate() {return this.dueDate;}
    public String getIdempotencyKey() {return this.idempotencyKey;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setMemberId(int memberId) {this.memberId = memberId;}
    public void setBookId(int bookId) {this.bookId = bookId;}
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}
    public void setIdempotencyKey(String idempotencyKey) {this.idempotencyKey = idempotencyKey;}
}
