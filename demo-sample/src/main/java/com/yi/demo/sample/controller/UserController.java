package com.yi.demo.sample.controller;

import com.yi.demo.sample.common.exception.YInfoNotFoundException;
import com.yi.demo.sample.model.YResult;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.model.entity.User;
import com.yi.demo.sample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public YResult query(UserQueryDTO queryParam) {
        return YResult.successWith(userService.query(queryParam));
    }

    @GetMapping("/{uid}")
    public YResult find(@PathVariable("uid") Long uid) {
        return YResult.successWith(userService.findOne(uid).orElseThrow(YInfoNotFoundException::new));
    }

    @PutMapping
    public YResult update(@RequestBody User user) {
        return YResult.successWith(userService.update(user));
    }

    @DeleteMapping("/{uid}")
    public YResult delete(@PathVariable("uid") Long uid) {
        userService.delete(uid);
        return YResult.successWith(uid);
    }


}
