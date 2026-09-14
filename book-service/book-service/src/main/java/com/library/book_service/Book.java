package com.library.book_service;

import jakarta.persistence.*;
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            private int id;
            private String title;
            private String author;
            private String genre;
            private boolean available;

            //Constructors
            public Book() {}
            public Book(String title, String author, String genre) {
                this.title = title;
                this.author = author;
                this.genre = genre;
                this.available = true;
            }
            public Book(int id, String title, String author, String genre) {
                this.id = id;
                this.title = title;
                this.author = author;
                this.genre = genre;
            }

            //Getters
            public int getId() {return this.id;}
            public String getTitle() {return this.title;}
            public String getAuthor() {return this.author;}
            public String getGenre() {return this.genre;}
            public boolean isAvailable() {return this.available;}

            //Setters
            public void setId(int id) {this.id = id;}
            public void setTitle(String title) {this.title = title;}
            public void setAuthor (String author) {this.author = author;}
            public void setGenre (String genre) {this.author = author;}
            public void setAvailable(boolean available) {this.available = available;}

}
