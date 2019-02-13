package cn.dsliang.library.service;

import cn.dsliang.library.entity.Biblio;
import org.springframework.data.domain.Page;

public interface BiblioService {
    Biblio save(Biblio biblio);

    Biblio findById(Integer id);

    Page<Biblio> list(String titleOrIsbn, Integer page, Integer size);

    void deleteById(Integer id);
}
