package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.repository.CollectionRepository;
import cn.dsliang.library.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionRepository collectionRepository;

    @Override
    public Collection save(Collection collection) {
        //生成种次号
        if (collection.getSerialNumber() == null) {
            Integer serial = 0;
            List<Collection> collections =
                    collectionRepository.findByCategoryNumber(collection.getCategoryNumber());
            for (Collection c : collections) {
                serial = Integer.max(serial, c.getSerialNumber());
            }

            serial++;
            collection.setSerialNumber(serial);
        }
        return collectionRepository.save(collection);
    }

    @Override
    public Collection findById(Integer id) {
        return collectionRepository.findOne(id);
    }

    @Override
    public Collection findByBarcode(String barcode) {
        return collectionRepository.findByBarcode(barcode);
    }

    @Override
    public Page<Collection> list(Integer biblioId, Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return collectionRepository.findByBiblioId(biblioId, pageable);
    }

    @Override
    public Integer countByLocationId(Integer id) {
        return collectionRepository.countByLocationId(id);
    }

    @Override
    public Integer countByBiblioId(Integer id) {
        return collectionRepository.countByBiblioId(id);
    }

    @Override
    public void deleteById(Integer id) {
        collectionRepository.delete(id);
    }
}
