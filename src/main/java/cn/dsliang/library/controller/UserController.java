package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.User;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.UserForm;
import cn.dsliang.library.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    ApiResponse<UserForm> findUser(@RequestParam(name = "userId", required = true) Integer id) {
        UserForm form = new UserForm();
        User user = userService.findById(id);
        if (user == null)
            throw new BusinessException(ResultEnum.USER_NOT_EXIST);

        BeanUtils.copyProperties(user, form);
        form.setStatusName(user.getStatusEnum().getMessage());

        return ApiResponse.success(form);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody UserForm form) {
        User rawUser = new User();
        if (form.getId() != null) {
            rawUser = userService.findById(form.getId());
            if (rawUser == null)
                throw new BusinessException(ResultEnum.USER_NOT_EXIST);
        } else {
            User u = userService.findByAccount(form.getAccount());
            if (u != null)
                throw new BusinessException(ResultEnum.USER_IS_EXIST);
        }

        BeanUtils.copyProperties(form, rawUser);

        userService.save(rawUser);

        return ApiResponse.success();
    }

    @PostMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<UserForm>> list(@RequestParam(required = false) String account,
                                                 @RequestParam(required = false) Integer status,
                                                 @RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        List<UserForm> forms = new ArrayList<>();
        Page<User> userPage = userService.list(account, status, page - 1, size);
        for (User user : userPage.getContent()) {
            UserForm form = new UserForm();
            BeanUtils.copyProperties(user, form);
            form.setStatusName(user.getStatusEnum().getMessage());

            forms.add(form);
        }

        return ApiResponse.success(
                new EasyuiPageResult<UserForm>(userPage.getTotalElements(), forms));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "userId", required = true) Integer id) {
        userService.deleteById(id);

        return ApiResponse.success();
    }
}
