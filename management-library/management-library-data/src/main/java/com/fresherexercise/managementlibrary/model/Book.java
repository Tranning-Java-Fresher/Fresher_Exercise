/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.managementlibrary.model;

import javax.persistence.*;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "authorId")
    private long author;
    @Column(name = "typeId")
    private long type;
    @Column(name = "description")
    private String description;
    @Column(name = "isDelete")
    private boolean isDelete;

    public Book() {
    }

    public Book(long id, String name, long author, long type, String description, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.description = description;
        this.isDelete = isDelete;
    }

    public Book(String name, long author, long type, String description) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.description = description;
        this.isDelete = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", author=" + author + ", type=" + type + ", description=" + description + ", isDelete=" + isDelete + '}';
    }

    
}
