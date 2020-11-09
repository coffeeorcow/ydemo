package com.yi.demo.sample.service.impl;

import com.google.common.collect.Lists;
import com.yi.demo.sample.common.exception.YInfoNotFoundException;
import com.yi.demo.sample.model.dto.user.UserDTO;
import com.yi.demo.sample.model.dto.user.UserInfoDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.model.entity.User;
import com.yi.demo.sample.repository.UserRepository;
import com.yi.demo.sample.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void insert(UserDTO user) {
        userRepository.save(user.convertToUser());
    }

    @Override
    public void update(UserDTO user) {
        Optional<User> userOptional = userRepository.findById(user.getUid());
        User oldUserInfo = userOptional.orElseThrow(() -> new YInfoNotFoundException("用户不存在"));
        BeanUtils.copyProperties(user, oldUserInfo);
        userRepository.save(oldUserInfo);
    }

    @Override
    public void delete(Long uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public Optional<UserInfoDTO> findOne(Long uid) {
        User user = userRepository.findByUid(uid);
        return Optional.ofNullable(user != null ? new UserInfoDTO().convertFor(user) : null);
    }

    @Override
    public List<UserInfoDTO> query(UserQueryDTO queryParam) {
        return Lists.newArrayList();
    }

}
