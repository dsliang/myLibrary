package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Location;
import cn.dsliang.library.repository.LocationRepository;
import cn.dsliang.library.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findOne(id);
    }

    @Override
    public Page<Location> list(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return locationRepository.findAll(pageable);
    }

    @Override
    public void deleteBydId(Integer id) {
        locationRepository.delete(id);
    }
}
