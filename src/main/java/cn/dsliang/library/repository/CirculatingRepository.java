package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Circulating;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CirculatingRepository extends JpaRepository<Circulating, Integer> {
    List<Circulating> findByReaderId(Integer readerId);
}
