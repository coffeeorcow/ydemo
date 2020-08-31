package com.yi.demo.sample.service.impl;

import com.yi.demo.sample.model.dto.user.UserInfoDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.model.entity.User;
import com.yi.demo.sample.repository.UserRepository;
import com.yi.demo.sample.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public Optional<User> findOne(Long uid) {
        return userRepository.findById(uid);
    }

    @Override
    public List<UserInfoDTO> query(UserQueryDTO queryParam) {

        return null;
    }

}
