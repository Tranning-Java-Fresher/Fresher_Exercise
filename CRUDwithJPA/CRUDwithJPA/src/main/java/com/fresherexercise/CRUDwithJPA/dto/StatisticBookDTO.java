/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.dto;

/**
 *
 * @author Admin
 */
public class StatisticBookDTO {
    private String authorName;
    private int count;

    public StatisticBookDTO() {
    }

    public StatisticBookDTO(String authorName, int count) {
        this.authorName = authorName;
        this.count = count;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
