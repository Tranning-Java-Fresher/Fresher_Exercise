/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.springConfig.controller;

import com.fresherexercise.springConfig.model.Advertisers;
import com.fresherexercise.springConfig.model.Publishers;
import com.fresherexercise.springConfig.repository.AdvertiserRepository;
import com.fresherexercise.springConfig.repository.PublisherRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    AdvertiserRepository advertiserRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @ResponseBody
    @RequestMapping(path = "/")
    public String home() {

        LOGGER.trace("This is TRACE");
        LOGGER.debug("This is DEBUG");
        LOGGER.info("This is INFO");
        LOGGER.warn("This is WARN");
        LOGGER.error("This is ERROR");

        return "Hi, show loggings in the console or file!";
    }

    @GetMapping("/advertiser/list")
    public ResponseEntity<List<Advertisers>> getAllAdvertiser(@RequestParam(required = false) String title) {
        try {
            List<Advertisers> advertisers = new ArrayList<Advertisers>();
            if (title == null) {
                advertiserRepository.findAll().forEach(advertisers::add);
            }
            if (advertisers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(advertisers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/publisher/list")
    public ResponseEntity<List<Publishers>> getAllPublisher(@RequestParam(required = false) String title) {
        try {
            List<Publishers> publishers = new ArrayList<Publishers>();
            if (title == null) {
                publisherRepository.findAll().forEach(publishers::add);
            }
            if (publishers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(publishers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
