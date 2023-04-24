package com.hnust.service.impl;

import com.hnust.dao.UserDao;
import com.hnust.entity.User;
import com.hnust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 长夜
 * @date 2023/4/15 21:59
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User selectByEmail(String email) {
        return userDao.selectByEmail(email);
    }
}
