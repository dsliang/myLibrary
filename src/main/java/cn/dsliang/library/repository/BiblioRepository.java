package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Biblio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiblioRepository extends JpaRepository<Biblio, Integer> {
}
