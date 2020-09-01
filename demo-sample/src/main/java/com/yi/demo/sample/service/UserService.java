package com.yi.demo.sample.service;

import com.yi.demo.sample.model.dto.user.UserDTO;
import com.yi.demo.sample.model.dto.user.UserInfoDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void insert(UserDTO user);

    void update(UserDTO user);

    void delete(Long uid);

    Optional<UserInfoDTO> findOne(Long uid);

    List<UserInfoDTO> query(UserQueryDTO queryParam);
}
