package com.zxk.springbootstart.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class User {
    @NotNull
    private Integer id;
    private String pictureUrl;
    private String phoneNumber;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "^\\S{2,16}$")
    private String nickname;
    private String username;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING) // 存储枚举的字符串值
    private Role role;
    public enum Role {
        ADMIN,  // 管理员
        USER    // 普通用户
    }
}
