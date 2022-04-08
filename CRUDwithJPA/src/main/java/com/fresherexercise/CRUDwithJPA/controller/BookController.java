/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.controller;

import com.fresherexercise.CRUDwithJPA.dto.StatisticBookByAuthorDTO;
import com.fresherexercise.CRUDwithJPA.dto.StatisticBookByTypeDTO;
import com.fresherexercise.CRUDwithJPA.model.Book;
import com.fresherexercise.CRUDwithJPA.repository.BookRepository;
import com.fresherexercise.CRUDwithJPA.service.StatisticService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    StatisticService statisticService;

    @GetMapping("/getAllBook")
    public ResponseEntity<List<Book>> getAllBook(@RequestParam(required = false) String title) {
        try {
            List<Book> books = new ArrayList<Book>();
            if (title == null) {
                bookRepository.findAll().forEach(books::add);
            }
//            else {
//                findByNameContaining(title);
//            }
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Optional<Book> authorData = bookRepository.findById(id);
        if (authorData.isPresent()) {
            return new ResponseEntity<>(authorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setName(book.getName());
            _book.setDescription(book.getDescription());
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deActiveBook/{id}")
    public ResponseEntity<Book> deActiveBook(@PathVariable("id") long id) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setIsDelete(true);
            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            Book _book = bookRepository
                    .save(new Book(book.getName(), book.getAuthor(), book.getType(), book.getDescription()));
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/statisticBookByAuthor")
    public List<StatisticBookByAuthorDTO> statisticBookByAuthor() {
        return statisticService.getNumberOfBookByAuthor();
    }

    @GetMapping("/statisticBookByType")
    public List<StatisticBookByTypeDTO> statisticBookByType() {
        return statisticService.getNumberOfBookByType();
    }
}
