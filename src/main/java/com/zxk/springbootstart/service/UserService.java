package com.zxk.springbootstart.service;

import com.zxk.springbootstart.pojo.User;

public interface UserService {
    User findByEmail(String email);
    User findByUsername(String username);
    void register(String username,String password,String email);
    User findById(Integer id);
    void update(User user);
    boolean userExist(String username, String email);
    void updatePicture(String pictureUrl);
    void updatePassword(String newPwd);
}
