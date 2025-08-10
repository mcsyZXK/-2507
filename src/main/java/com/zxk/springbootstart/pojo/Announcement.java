package com.zxk.springbootstart.pojo;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Announcement {
    private Integer id;
    private String title;
    private String message;
    private Timestamp updatedAt;
    private Long likeCount;
}
