package com.yi.demo.sample.model.dto.user;

import com.google.common.base.Converter;
import com.yi.demo.sample.model.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.annotation.ParametersAreNonnullByDefault;
import java.time.LocalDateTime;

@Data
public class UserInfoDTO {

    private String uid;
    private String name;
    private String remarks;
    private LocalDateTime createTime;
    public User convertToUser() {
        UserInfoDTOConverter converter = new UserInfoDTOConverter();
        return converter.doForward(this);
    }

    public UserInfoDTO convertFor(User user) {
        UserInfoDTOConverter converter = new UserInfoDTOConverter();
        return converter.doBackward(user);
    }

    private static class UserInfoDTOConverter extends Converter<UserInfoDTO, User> {

        @Override
        @ParametersAreNonnullByDefault
        protected User doForward(UserInfoDTO userDTO) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            return user;
        }

        @Override
        @ParametersAreNonnullByDefault
        protected UserInfoDTO doBackward(User user) {
            UserInfoDTO dto = new UserInfoDTO();
            BeanUtils.copyProperties(user, dto);
            return dto;
        }
    }


}
