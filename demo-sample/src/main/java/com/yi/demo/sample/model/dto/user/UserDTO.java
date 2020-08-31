package com.yi.demo.sample.model.dto.user;

import com.yi.demo.sample.validation.group.YGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull(message = "用户标识不能为空", groups = YGroup.Update.class)
    private Long uid;

    @Length(max = 50, min = 3, message = "长度在 3 ~ 50 之间")
    @NotNull(message = "用户名称不能为空", groups = YGroup.Insert.class)
    private String name;

    @NotNull(message = "邮箱地址不能为空", groups = YGroup.Insert.class)
    @Email(message = "邮箱格式有误")
    private String email;

    @Length(max = 255, message = "最大长度不超过 255 个字符")
    private String remarks;

}
