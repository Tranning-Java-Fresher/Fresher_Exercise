/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.managementlibrarydata.model;

import javax.persistence.*;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "isDelete")
    private boolean isDelete;

    public Type() {
    }

    public Type(long id, String name, String description, boolean isDelete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDelete = isDelete;
    }
    
    public Type( String name, String description) {
        this.name = name;
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
        return "Type{" + "id=" + id + ", name=" + name + ", description=" + description + ", isDelete=" + isDelete + '}';
    }
    

    

}
