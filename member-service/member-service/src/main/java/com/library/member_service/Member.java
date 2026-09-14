package com.library.member_service;

import jakarta.persistence.*;
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //Constructors
    public Member() {}
    public Member(String name) {this.name = name;}
    public Member(String name, int id) {
        this.id = id;
        this.name = name;
    }

    //Getters
    public int getId() {return this.id;}
    public String getName() {return this.name;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
}
