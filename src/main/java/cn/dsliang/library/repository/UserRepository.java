package cn.dsliang.library.repository;

import cn.dsliang.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);
}
