/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.repository;

import com.fresherexercise.CRUDwithJPA.model.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    
//    List<Author> findByNameContaining(String title);
   
    //List<Author> FindByName(String nameInput);
    
    
}
