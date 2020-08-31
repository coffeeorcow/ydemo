package com.yi.demo.sample.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yi.demo.sample.model.dto.user.UserInfoDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.model.entity.QUser;
import com.yi.demo.sample.model.entity.User;
import com.yi.demo.sample.repository.UserRepository;
import com.yi.demo.sample.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
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
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getUid());
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
