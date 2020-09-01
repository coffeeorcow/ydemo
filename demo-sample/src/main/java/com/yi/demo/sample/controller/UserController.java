package com.yi.demo.sample.controller;

import com.yi.demo.sample.common.exception.YInfoNotFoundException;
import com.yi.demo.sample.model.YResult;
import com.yi.demo.sample.model.dto.user.UserDTO;
import com.yi.demo.sample.model.dto.user.UserInfoDTO;
import com.yi.demo.sample.model.dto.user.UserQueryDTO;
import com.yi.demo.sample.service.UserService;
import com.yi.demo.sample.validation.group.YGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;

/**
 * 用户信息
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 查询用户
     *
     * @param queryParam 查询参数
     * @return 用户信息
     */
    @GetMapping
    public YResult<UserInfoDTO> query(UserQueryDTO queryParam) {
        return YResult.successWith(userService.query(queryParam));
    }

    /**
     * 获取单个用户信息
     *
     * @param uid 用户标识
     * @return 用户信息
     */
    @GetMapping("/{uid}")
    public YResult<UserInfoDTO> find(@PathVariable("uid") Long uid) {
        return YResult.successWith(userService.findOne(uid).orElseThrow(YInfoNotFoundException::new));
    }

    /**
     * 插入用户信息
     *
     * @param user 用户信息
     * @return 是否成功
     */
    @PostMapping
    public YResult<?> insert(@RequestBody @Validated({YGroup.Insert.class, Default.class}) UserDTO user) {
        userService.insert(user);
        return YResult.success();
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 是否成功
     */
    @PutMapping
    public YResult<?> update(@RequestBody @Validated({YGroup.Update.class, Default.class}) UserDTO user) {
        userService.update(user);
        return YResult.success();
    }

    /**
     * 删除用户
     *
     * @param uid 用户标识
     * @return 是否成功
     */
    @DeleteMapping("/{uid}")
    public YResult<?> delete(@PathVariable("uid") Long uid) {
        userService.delete(uid);
        return YResult.successWith(uid);
    }

}
