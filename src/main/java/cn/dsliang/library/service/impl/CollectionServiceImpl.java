package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.repository.CollectionRepository;
import cn.dsliang.library.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionRepository collectionRepository;

    @Override
    public Collection save(Collection collection) {
        return collectionRepository.save(collection);
    }

    @Override
    public Collection findById(Integer id) {
        return collectionRepository.findOne(id);
    }

    @Override
    public Page<Collection> list(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return collectionRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        collectionRepository.delete(id);
    }
}
