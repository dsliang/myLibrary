package cn.dsliang.library.service;

import cn.dsliang.library.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User save(User user);

    User findById(Integer id);

    Page<User> list(String account, Integer status, Integer page, Integer size);

    void deleteById(Integer id);
}
