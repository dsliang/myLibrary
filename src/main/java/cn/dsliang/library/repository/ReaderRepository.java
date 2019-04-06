package cn.dsliang.library.repository;

import cn.dsliang.library.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    Reader findByCard(String card);
}
