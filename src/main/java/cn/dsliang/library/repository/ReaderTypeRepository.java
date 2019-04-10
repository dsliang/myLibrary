package cn.dsliang.library.repository;

import cn.dsliang.library.entity.ReaderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderTypeRepository extends JpaRepository<ReaderType, Integer> {
    ReaderType findByName(String name);

    Integer countByRuleId(Integer id);
}
