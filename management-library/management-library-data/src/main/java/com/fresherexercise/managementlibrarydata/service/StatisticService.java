/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fresherexercise.managementlibrarydata.service;

import com.fresherexercise.managementlibrarydata.dto.StatisticBookByAuthorDTO;
import com.fresherexercise.managementlibrarydata.dto.StatisticBookByTypeDTO;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface StatisticService {
    List<StatisticBookByAuthorDTO> getNumberOfBookByAuthor();
    List<StatisticBookByTypeDTO> getNumberOfBookByType();
}
