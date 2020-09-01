package com.yi.demo.sample.controller;

import com.yi.demo.sample.common.exception.YInfoNotFoundException;
import com.yi.demo.sample.model.YResult;
import com.yi.demo.sample.model.dto.user.UserDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.service.UserService;
import com.yi.demo.sample.validation.group.YGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;

@Validated
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

    @PostMapping
    public YResult insert(@RequestBody @Validated({YGroup.Insert.class, Default.class}) UserDTO user) {
        userService.insert(user);
        return YResult.success();
    }

    @PutMapping
    public YResult update(@RequestBody @Validated({YGroup.Update.class, Default.class}) UserDTO user) {
        userService.update(user);
        return YResult.success();
    }

    @DeleteMapping("/{uid}")
    public YResult delete(@PathVariable("uid") Long uid) {
        userService.delete(uid);
        return YResult.successWith(uid);
    }


}
