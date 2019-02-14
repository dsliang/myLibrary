package cn.dsliang.library.service;

import cn.dsliang.library.entity.Reader;
import org.springframework.data.domain.Page;

public interface ReaderService {
    Reader save(Reader reader);

    Reader findById(Integer id);

    Page<Reader> list(String readerName, Integer status, Integer page, Integer size);

    void deleteById(Integer id);
}
