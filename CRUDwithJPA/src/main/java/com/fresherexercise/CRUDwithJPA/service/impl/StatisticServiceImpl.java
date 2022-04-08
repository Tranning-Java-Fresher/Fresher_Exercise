/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.service.impl;

import com.fresherexercise.CRUDwithJPA.controller.BookController;
import com.fresherexercise.CRUDwithJPA.dto.StatisticBookByAuthorDTO;
import com.fresherexercise.CRUDwithJPA.dto.StatisticBookByTypeDTO;
import com.fresherexercise.CRUDwithJPA.service.StatisticService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
    @Autowired
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

    @Override
    public List<StatisticBookByAuthorDTO> getNumberOfBookByAuthor() {
        Session session = openSession();
        try {
            Query query = session.createNativeQuery("SELECT author.name, count(*) FROM book JOIN author ON book.author_id = author.id GROUP BY author.name");
            List<Object[]> result = query.getResultList();
            List<StatisticBookByAuthorDTO> sbd = result.stream().map(item -> {
                StatisticBookByAuthorDTO statisticBookByAuthorDTO = new StatisticBookByAuthorDTO();
                statisticBookByAuthorDTO.setAuthorName(item[0].toString());
                statisticBookByAuthorDTO.setCount(Integer.parseInt(item[1].toString()));
                return statisticBookByAuthorDTO;
            }).collect(Collectors.toList());
            return (List<StatisticBookByAuthorDTO>) sbd;
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
            ex.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

    @Override
    public List<StatisticBookByTypeDTO> getNumberOfBookByType() {
        Session session = openSession();
        try {
            Query query = session.createNativeQuery("SELECT author.name, count(*) FROM book JOIN author ON book.author_id = author.id GROUP BY author.name");
            List<Object[]> result = query.getResultList();
            List<StatisticBookByTypeDTO> sbd = result.stream().map(item -> {
                StatisticBookByTypeDTO statisticBookByTypeDTO = new StatisticBookByTypeDTO();
                statisticBookByTypeDTO.setType(item[0].toString());
                statisticBookByTypeDTO.setCount(Integer.parseInt(item[1].toString()));
                return statisticBookByTypeDTO;
            }).collect(Collectors.toList());
            return (List<StatisticBookByTypeDTO>) sbd;
        } catch (Exception ex) {
            LOGGER.error(ex.toString());
            ex.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }

}
