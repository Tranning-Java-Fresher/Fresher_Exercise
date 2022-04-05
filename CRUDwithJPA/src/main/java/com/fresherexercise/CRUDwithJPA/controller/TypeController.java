/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.controller;

import com.fresherexercise.CRUDwithJPA.model.Type;
import com.fresherexercise.CRUDwithJPA.repository.TypeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @GetMapping("/getAllType")
    public ResponseEntity<List<Type>> getAllType(@RequestParam(required = false) String title) {
        try {
            List<Type> types = new ArrayList<Type>();
            if (title == null) {
                typeRepository.findAll().forEach(types::add);
            }
//            else {
//                findByNameContaining(title);
//            }
            if (types.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(types, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    public ResponseEntity<List<Author>> findByNameContaining(String name) {
//        try {
//            List<Author> authors = new ArrayList<Author>();
//            if (authorRepository.) {  
//            }
//            return new ResponseEntity<>(authors, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/getTypeById/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable("id") long id) {
        Optional<Type> typeData = typeRepository.findById(id);
        if (typeData.isPresent()) {
            return new ResponseEntity<>(typeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateType/{id}")
    public ResponseEntity<Type> updateType(@PathVariable("id") long id, @RequestBody Type type) {
        Optional<Type> typeData = typeRepository.findById(id);
        if (typeData.isPresent()) {
            Type _type = typeData.get();
            _type.setName(type.getName());
            _type.setDescription(type.getDescription());
            return new ResponseEntity<>(typeRepository.save(_type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deActiveType/{id}")
    public ResponseEntity<Type> deActiveType(@PathVariable("id") long id) {
        Optional<Type> typeData = typeRepository.findById(id);
        if (typeData.isPresent()) {
            Type _type = typeData.get();
            _type.setIsDelete(true);
            return new ResponseEntity<>(typeRepository.save(_type), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addType")
    public ResponseEntity<Type> addType(@RequestBody Type type) {
        try {
            Type _type = typeRepository
                    .save(new Type(type.getName(), type.getDescription()));
            return new ResponseEntity<>(_type, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
