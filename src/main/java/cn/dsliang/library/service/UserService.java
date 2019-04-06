package cn.dsliang.library.service;

import cn.dsliang.library.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User save(User user);

    User findById(Integer id);

    User findByAccount(String account);

    Page<User> list(String account, Integer status, Integer page, Integer size);

    void deleteById(Integer id);

    void login(String account, String password);

    void logout();
}
