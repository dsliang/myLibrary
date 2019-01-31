package cn.dsliang.library.service;

import cn.dsliang.library.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Integer id);

    Page<User> list(Integer page, Integer size);

    void deleteBydId(Integer id);
}
