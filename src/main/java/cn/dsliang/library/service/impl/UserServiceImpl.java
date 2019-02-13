package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.User;
import cn.dsliang.library.repository.UserRepository;
import cn.dsliang.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
