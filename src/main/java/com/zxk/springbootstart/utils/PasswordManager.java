package com.zxk.springbootstart.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordManager {
    // 哈希密码
    public static String hashPassword(String password) {
        // 生成哈希密码
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // 验证密码
    public static boolean checkPassword(String password, String hashedPassword) {
        // 检查输入的密码与存储的哈希密码是否匹配
        return BCrypt.checkpw(password, hashedPassword);
    }

}
