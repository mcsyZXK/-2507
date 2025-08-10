package com.zxk.springbootstart.service.impl;

import com.zxk.springbootstart.mapper.UserMapper;
import com.zxk.springbootstart.pojo.User;
import com.zxk.springbootstart.service.UserService;
import com.zxk.springbootstart.utils.PasswordManager;
import com.zxk.springbootstart.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByEmail(String email){
        return userMapper.findByEmail(email);
    }
    @Override
    public boolean userExist(String username, String email){return userMapper.userExist(username,email);};
    @Override
    public User findByUsername(String username) {return userMapper.findByUsername(username);}
    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
    @Override
    public void register(String username, String password,String email) {
        String jmpswd = PasswordManager.hashPassword(password);
        userMapper.register(username,jmpswd,email);
    }
    @Override
    public void update(User user) {
        userMapper.update(user);
    }
    @Override
    public void updatePicture(String pictureUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        userMapper.updatePicture(pictureUrl, id);
    }
    @Override
    public void updatePassword(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        userMapper.updatePassword(PasswordManager.hashPassword(newPwd));
    }
}
