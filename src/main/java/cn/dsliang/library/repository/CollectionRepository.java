package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    Collection findByBarcode(String barcode);

    Page<Collection> findByBiblioId(Integer biblioId, Pageable pageable);
}
