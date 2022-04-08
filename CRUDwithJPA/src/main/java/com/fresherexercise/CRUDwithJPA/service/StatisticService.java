/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fresherexercise.CRUDwithJPA.service;

import com.fresherexercise.CRUDwithJPA.dto.StatisticBookByAuthorDTO;
import com.fresherexercise.CRUDwithJPA.dto.StatisticBookByTypeDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatisticService {
    List<StatisticBookByAuthorDTO> getNumberOfBookByAuthor();
    List<StatisticBookByTypeDTO> getNumberOfBookByType();
}
