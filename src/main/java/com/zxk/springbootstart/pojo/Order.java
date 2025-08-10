package com.zxk.springbootstart.pojo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Order {
    private int orderId;
    private int userId;
    private String cheNum;
    private LocalDate createdAt;
    public enum Status {
        PENDING,
        REJECTED,
        APPROVED,
        CANCELLED
    }
    @Enumerated(EnumType.STRING) // 存储枚举的字符串值
    private Status status;
    private LocalDate requestDate;

}
