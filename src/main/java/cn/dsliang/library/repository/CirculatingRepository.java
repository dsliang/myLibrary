package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Circulating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CirculatingRepository extends JpaRepository<Circulating, Integer> {
    List<Circulating> findByReaderId(Integer readerId);

    Page<Circulating> findByReaderId(Integer readerId, Pageable pageable);

    Circulating findByCollectionId(Integer collectionId);
}
