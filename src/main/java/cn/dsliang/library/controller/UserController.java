package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.User;
import cn.dsliang.library.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    ApiResponse<User> findUser(@RequestParam(name = "userId", required = true) Integer id) {
        User user = userService.findById(id);
        if (user == null)
            return ApiResponse.error("用户不存在");
        return ApiResponse.success(user);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody User user) {
        User rawUser = new User();
        if (user.getUserId() != null) {
            rawUser = userService.findById(user.getUserId());
            if (rawUser == null)
                return ApiResponse.error("用户不存在");
        }

        BeanUtils.copyProperties(user, rawUser);
        userService.save(rawUser);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<User>> list(@RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<User> userPage = userService.list(page - 1, size);
        return ApiResponse.success(
                new EasyuiPageResult<User>(userPage.getTotalElements(), userPage.getContent()));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "userId", required = true) Integer id) {
        userService.deleteBydId(id);
        return ApiResponse.success();
    }
}
