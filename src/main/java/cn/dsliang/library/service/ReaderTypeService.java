package cn.dsliang.library.service;

import cn.dsliang.library.entity.Location;
import cn.dsliang.library.entity.ReaderType;
import org.springframework.data.domain.Page;

public interface ReaderTypeService {
    ReaderType save(ReaderType readerType);

    ReaderType findById(Integer id);

    Page<ReaderType> list(Integer page, Integer size);

    void deleteById(Integer id);
}
