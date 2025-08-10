package com.zxk.springbootstart.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Reply {
    private Integer id;
    private Integer anId;
    private String retext;
    private String nickname;
    private Date timestamp;
}
