package com.zxk.springbootstart.controller;


import com.zxk.springbootstart.pojo.Result;
import com.zxk.springbootstart.pojo.User;
import com.zxk.springbootstart.service.UserService;
import com.zxk.springbootstart.utils.JwtUtil;
import com.zxk.springbootstart.utils.PasswordManager;
import com.zxk.springbootstart.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @RequestMapping("/findByEmail")
    public User findByEmail(String email) {
        return userService.findByEmail(email);
    }
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password, @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")String email){
        boolean b = userService.userExist(username,email);
        if (!b){
            userService.register(username,password,email);
            return Result.success();
        }else {
            return Result.error("已存在用户");
        }
    }
    @PostMapping("/login")
    public Result login(String emailOrUsername, String password) {
        User loginUser = userService.findByEmail(emailOrUsername);
        if (loginUser == null) {
            loginUser = userService.findByUsername(emailOrUsername);
        }
        if (loginUser == null || !PasswordManager.checkPassword(password, loginUser.getPassword())) {
            return Result.error("用户名或密码错误");
        }
        Map<String, Object> user = new HashMap<>();
        user.put("username", loginUser.getUsername());
        user.put("nickname", loginUser.getNickname());
        user.put("email", loginUser.getEmail());
        user.put("id", loginUser.getId());
        user.put("rol6+e", loginUser.getRole().name());
        user.put("pictureUrl", loginUser.getPictureUrl());
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwtUtil.genToken(user));
        response.put("user", user);
        return Result.success(response);
    }
    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            jwtUtil.addToBlacklist(token.substring(7));
        }
        ThreadLocalUtil.remove();
        return Result.success("退出成功");
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object> o = ThreadLocalUtil.get();
        String username = (String) o.get("username");
        User user=userService.findByUsername(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updatePicture")
    public Result updatePicture(@RequestParam @URL String pictureUrl){
        userService.updatePicture(pictureUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺参");
        }
        Map<String,Object> map=ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUsername(username);
        if(!loginUser.getPassword().equals(PasswordManager.hashPassword(oldPwd))){
            return Result.error("原密码错误");
        }
        if(!rePwd.equals(newPwd)){
            return Result.error("两次密码不一致");
        }
        userService.updatePassword(newPwd);
        return Result.success();
    }
}
