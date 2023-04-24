package com.hnust.dao;

import com.hnust.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 长夜
 * @date 2023/3/20 19:55
 */

public interface CourseDao {
    void insertAutoId(Course course);
    void deleteById(Integer id);
    void update(Course course);
    List<Course> selectAll();
    Course selectById(Integer id);
    List<Course> selectBycName(String name);
    List<Course> selectByscName(String name);
    List<Course> selectByCondition(Course course);
    List<Course> selectByConditionSingle(Course course);
    String selectImage(Integer id);
}
