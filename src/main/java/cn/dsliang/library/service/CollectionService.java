package cn.dsliang.library.service;

import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Collection;
import org.springframework.data.domain.Page;

public interface CollectionService {
    Collection save(Collection collection);

    Collection findById(Integer id);

    Collection findByBarcode(String barcode);

    Page<Collection> list(Integer page, Integer size);

    void deleteById(Integer id);
}
