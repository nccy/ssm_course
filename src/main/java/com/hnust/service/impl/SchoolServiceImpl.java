package com.hnust.service.impl;

import com.hnust.dao.SchoolDao;
import com.hnust.entity.School;
import com.hnust.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 长夜
 * @date 2023/4/21 14:24
 */
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolDao schoolDao;

    @Override
    public School selectSchoolNameById(int id) {
        return schoolDao.selectSchoolNameById(id);
    }

    @Override
    public List<School> selectAll() {
        return schoolDao.selectAll();
    }
}
