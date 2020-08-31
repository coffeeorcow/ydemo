package com.yi.demo.sample.service;

import com.yi.demo.sample.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("xxx");
        user.setEmail("fsadfsd@email.com");
        user.setRemarks("test");
        userService.save(user);

        log.info("insert user: \n{}", user);
    }

    @Test
    public void test() {
        log.info("test");
    }

}
