package com.library.borrowing_service.dto;

public class BookDto {
    private int id;
    private String title;
    private boolean available;

    //Getters
    public int getId() {return this.id;}
    public String getTitle() {return this.title;}
    public boolean isAvailable() {return this.available;}

    //Setters
    public void setId(int id ) { this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAvailable(boolean available) {this.available = available;}
}
