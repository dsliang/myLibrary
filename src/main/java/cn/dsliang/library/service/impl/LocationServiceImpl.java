package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Location;
import cn.dsliang.library.repository.LocationRepository;
import cn.dsliang.library.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Location findByName(String name) {
        return locationRepository.findByName(name);
    }

    @Override
    public Page<Location> list(String name, Integer page, Integer size) {
        Location location = new Location();
        location.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Location> example = Example.of(location, matcher);
        Pageable pageable = new PageRequest(page, size);

        return locationRepository.findAll(example, pageable);
    }

    @Override
    public void deleteById(Integer id) {
        locationRepository.delete(id);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
