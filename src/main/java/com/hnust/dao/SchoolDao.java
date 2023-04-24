package com.hnust.dao;

import com.hnust.entity.School;

import java.util.List;

/**
 * @author 长夜
 * @date 2023/4/21 14:11
 */
public interface SchoolDao {
    School selectSchoolNameById(int id);

    List<School> selectAll();
}
