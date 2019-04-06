package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.entity.User;
import cn.dsliang.library.from.LoginUserInfoForm;
import cn.dsliang.library.helper.SecurityHelper;
import cn.dsliang.library.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("/api/common")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    ApiResponse login(String account, String password) {
        userService.login(account, password);

        return ApiResponse.success();
    }

    @GetMapping("/logout")
    @ResponseBody
    ApiResponse logout() {
        userService.logout();

        return ApiResponse.success();
    }

    @GetMapping("/auth")
    @ResponseBody
    ApiResponse<LoginUserInfoForm> auth() {
        LoginUserInfoForm form = new LoginUserInfoForm();
        User user = SecurityHelper.getUser();
        BeanUtils.copyProperties(user, form);

        return ApiResponse.success(form);
    }
}
