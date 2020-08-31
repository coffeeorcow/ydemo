package com.yi.demo.sample.model.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoDTO {

    private String uid;
    private String name;
    private String remarks;
    private LocalDateTime createTime;

}
