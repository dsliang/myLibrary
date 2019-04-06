package cn.dsliang.library.service;

import cn.dsliang.library.entity.Location;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService {
    Location save(Location location);

    Location findById(Integer id);

    Location findByName(String name);

    Page<Location> list(String name, Integer page, Integer size);

    void deleteById(Integer id);

    List<Location> findAll();
}
