/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name = "name")
    private String Name;
    @Column(name = "authorId")
    private long Author;
    @Column(name = "typeId")
    private String Type;
    @Column(name = "description")
    private String Description;

    public Book() {
    }

    public Book(int Id, String Name, long Author, String Type, String Description) {
        this.Id = Id;
        this.Name = Name;
        this.Author = Author;
        this.Type = Type;
        this.Description = Description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public long getAuthor() {
        return Author;
    }

    public void setAuthor(long Author) {
        this.Author = Author;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Book{" + "Id=" + Id + ", Name=" + Name + ", Author=" + Author + ", Type=" + Type + ", Description=" + Description + '}';
    }

    
    
    
}
