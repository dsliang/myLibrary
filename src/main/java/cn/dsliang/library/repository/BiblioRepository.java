package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Biblio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BiblioRepository extends JpaRepository<Biblio, Integer>  , JpaSpecificationExecutor<Biblio> {
    Page<Biblio> findByTitleLikeOrIsbnLike(String title, String isbn, Pageable pageable);
}
