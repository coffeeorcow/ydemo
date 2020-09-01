package com.yi.demo.sample.model.dto.user;

import com.google.common.base.Converter;
import com.yi.demo.sample.model.entity.User;
import com.yi.demo.sample.validation.group.YGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Data
public class UserDTO {

    @NotNull(message = "用户标识不能为空", groups = YGroup.Update.class)
    private Long uid;

    @Length(max = 50, min = 3, message = "长度在 3 ~ 50 之间", groups = Default.class)
    @NotNull(message = "用户名称不能为空", groups = YGroup.Insert.class)
    private String name;

    @NotNull(message = "邮箱地址不能为空", groups = YGroup.Insert.class)
    @Email(message = "邮箱格式有误", groups = Default.class)
    private String email;

    @Length(max = 255, message = "最大长度不超过 255 个字符")
    private String remarks;

    public User convertToUser() {
        UserDTOConverter converter = new UserDTOConverter();
        return converter.doForward(this);
    }

    public UserDTO convertFor(User user) {
        return new UserDTOConverter().doBackward(user);
    }

    private static class UserDTOConverter extends Converter<UserDTO, User> {

        @ParametersAreNonnullByDefault
        @Override
        protected User doForward(UserDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }

        @ParametersAreNonnullByDefault
        @Override
        protected UserDTO doBackward(User user) {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);
            return dto;
        }
    }

}
