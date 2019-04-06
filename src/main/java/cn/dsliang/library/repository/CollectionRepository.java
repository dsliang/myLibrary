package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    Collection findByBarcode(String barcode);

    List<Collection> findByCategoryNumber(String categoryNumber);

    Page<Collection> findByBiblioId(Integer biblioId, Pageable pageable);
}
