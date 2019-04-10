package cn.dsliang.library.service.impl;

import cn.dsliang.library.repository.CirculatingRepository;
import cn.dsliang.library.service.CirculatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CirculatingServiceImpl implements CirculatingService {

    @Autowired
    CirculatingRepository circulatingRepository;

    @Override
    public Integer countByReaderId(Integer id) {
        return circulatingRepository.countByReaderId(id);
    }
}
