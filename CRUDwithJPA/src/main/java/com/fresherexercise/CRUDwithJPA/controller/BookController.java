/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.controller;

import com.fresherexercise.CRUDwithJPA.dto.StatisticBookDTO;
import com.fresherexercise.CRUDwithJPA.model.Book;
import com.fresherexercise.CRUDwithJPA.repository.BookRepository;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    private static SessionFactory sessionFactory;

    private Session openSession() {
        Session session = this.sessionFactory.openSession();
        return session;
    }

    private void closeSession(Session session) {
        if (session.isOpen()) {
            session.disconnect();
            session.close();
        }
    }

    @GetMapping("/statisticBook")
    public ResponseEntity<List<StatisticBookDTO>> getNumberOfBook() {
        Session session = openSession();
        try {
            Query query = session.createNativeQuery("SELECT author.name, count(*) FROM book JOIN author ON book.author_id = author.id GROUP BY author.name");
            //Query query = session.createNativeQuery(queryString.toString());
            List<Object[]> result = query.getResultList();
            List<StatisticBookDTO> sbd = result.stream().map(item -> {
                StatisticBookDTO statisticBookDTO = new StatisticBookDTO();
                statisticBookDTO.setAuthorName(item[0].toString());
                statisticBookDTO.setCount(Integer.parseInt(item[1].toString()));
                return statisticBookDTO;
            }).collect(Collectors.toList());
             return (ResponseEntity<List<StatisticBookDTO>>) sbd;
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
            ex.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }
    
    
    
}
