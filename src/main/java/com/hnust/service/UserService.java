package com.hnust.service;

import com.hnust.entity.User;

/**
 * @author 长夜
 * @date 2023/4/15 22:06
 */
public interface UserService {
    User selectByEmail(String email);
}
