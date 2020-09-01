package com.yi.demo.sample.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yi.demo.sample.common.exception.YInfoNotFoundException;
import com.yi.demo.sample.model.dto.user.UserDTO;
import com.yi.demo.sample.model.dto.user.UserInfoDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.model.entity.QUser;
import com.yi.demo.sample.model.entity.User;
import com.yi.demo.sample.repository.UserRepository;
import com.yi.demo.sample.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JPAQueryFactory queryFactory;

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
        QUser qUser = QUser.user;
        JPAQuery<User> query = queryFactory.selectFrom(qUser);

        BooleanBuilder builder = new BooleanBuilder();
        if (queryParam.getUid() != null) builder.and(qUser.uid.eq(queryParam.getUid()));
        if (Strings.isNotBlank(queryParam.getName())) builder.and(qUser.name.eq(queryParam.getName()));
        if (Strings.isNotBlank(queryParam.getNameLike())) builder.and(qUser.name.like(queryParam.getNameLike()));

        List<User> userList = query.where(builder).fetch();
        return userList.stream().map(user -> new UserInfoDTO().convertFor(user)).collect(Collectors.toList());
    }

}
