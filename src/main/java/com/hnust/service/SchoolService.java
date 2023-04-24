package com.hnust.service;

import com.hnust.entity.School;

import java.util.List;

/**
 * @author 长夜
 * @date 2023/4/21 14:24
 */
public interface SchoolService {
    School selectSchoolNameById(int id);
    List<School> selectAll();
}
