package cn.dsliang.library.service;

import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Reader;
import org.springframework.data.domain.Page;

public interface ReaderService {
    Reader save(Reader reader);

    Reader findById(Integer id);

    Page<Reader> list(Integer page, Integer size);

    void deleteById(Integer id);
}
