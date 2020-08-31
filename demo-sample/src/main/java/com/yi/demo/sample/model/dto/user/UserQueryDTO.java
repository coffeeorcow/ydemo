package com.yi.demo.sample.model.dto.user;

import lombok.Data;

@Data
public class UserQueryDTO {

    private Long uid;
    private String name;
    private String nameLike;

}
