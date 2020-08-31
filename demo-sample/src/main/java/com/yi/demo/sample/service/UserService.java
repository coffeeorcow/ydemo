package com.yi.demo.sample.service;

import com.yi.demo.sample.model.YResult;
import com.yi.demo.sample.model.dto.user.UserInfoDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    User update(User user);

    void delete(Long uid);

    Optional<User> findOne(Long uid);

    List<UserInfoDTO> query(UserQueryDTO queryParam);
}
