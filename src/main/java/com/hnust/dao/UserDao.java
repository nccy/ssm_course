package com.hnust.dao;

import com.hnust.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author 长夜
 * @date 2023/4/17 19:22
 */
public interface UserDao {
    User selectByEmail(String email);
}
