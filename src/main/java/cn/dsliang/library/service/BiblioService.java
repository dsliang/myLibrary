package cn.dsliang.library.service;

import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Location;
import org.springframework.data.domain.Page;

public interface BiblioService {
    Biblio save(Biblio biblio);

    Biblio findById(Integer id);

    Page<Biblio> list(Integer page, Integer size);

    void deleteById(Integer id);
}
