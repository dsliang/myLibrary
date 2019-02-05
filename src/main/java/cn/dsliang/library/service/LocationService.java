package cn.dsliang.library.service;

import cn.dsliang.library.entity.Location;
import cn.dsliang.library.entity.User;
import org.springframework.data.domain.Page;

public interface LocationService {
    Location save(Location location);

    Location findById(Integer id);

    Page<Location> list(Integer page, Integer size);

    void deleteById(Integer id);
}
