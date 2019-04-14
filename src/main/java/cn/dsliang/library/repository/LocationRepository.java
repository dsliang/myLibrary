package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findByName(String name);
}
