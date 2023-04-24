package com.hnust.service;

import com.hnust.entity.Course;

import java.util.List;

/**
 * @author 长夜
 * @date 2023/4/15 22:05
 */

public interface CourseService {
    Boolean insertAutoId(Course course);
    Boolean deleteById(Integer id);
    List<Course> selectAll();
    Course selectById(Integer id);
    Boolean update(Course course);
    List<Course> selectByscName(String name);
    String selectImage(Integer id);
}
