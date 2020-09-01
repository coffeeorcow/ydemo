package com.yi.demo.sample.model.dto.user;

import lombok.Data;

/**
 * 用户信息查询参数
 */
@Data
public class UserQueryDTO {

    /**
     * 用户标识
     */
    private Long uid;
    /**
     * 用户名称 - 精确匹配
     */
    private String name;
    /**
     * 用户名称模糊匹配
     */
    private String nameLike;

}
