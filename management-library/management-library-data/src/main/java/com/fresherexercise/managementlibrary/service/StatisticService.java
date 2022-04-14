/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fresherexercise.managementlibrary.service;

import com.fresherexercise.managementlibrary.dto.StatisticBookByAuthorDTO;
import com.fresherexercise.managementlibrary.dto.StatisticBookByTypeDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatisticService {
    List<StatisticBookByAuthorDTO> getNumberOfBookByAuthor();
    List<StatisticBookByTypeDTO> getNumberOfBookByType();
}
