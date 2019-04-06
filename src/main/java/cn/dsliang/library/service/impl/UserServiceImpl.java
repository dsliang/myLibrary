package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.User;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.enums.UserStatusEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.helper.SecurityHelper;
import cn.dsliang.library.repository.UserRepository;
import cn.dsliang.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public Page<User> list(String account, Integer status, Integer page, Integer size) {
        User user = new User();
        user.setAccount(account);
        user.setStatus(status);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("account", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<User> example = Example.of(user, matcher);
        Pageable pageable = new PageRequest(page, size);

        return userRepository.findAll(example, pageable);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public void login(String account, String password) {
        User user = userRepository.findByAccount(account);
        if (user == null)
            throw new BusinessException(ResultEnum.USER_NOT_EXIST);
        if (user.getStatusEnum() != UserStatusEnum.Valid)
            throw new BusinessException(ResultEnum.USER_STATUS_INVALID);
        if (!user.getPassword().equals(password))
            throw new BusinessException(ResultEnum.USER_PASSWORD_ERROR);

        SecurityHelper.setUser(user);
    }

    @Override
    public void logout() {
        SecurityHelper.removeUser();
    }
}
