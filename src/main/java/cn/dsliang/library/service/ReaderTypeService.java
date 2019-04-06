package cn.dsliang.library.service;

import cn.dsliang.library.entity.ReaderType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReaderTypeService {
    ReaderType save(ReaderType readerType);

    ReaderType findById(Integer id);

    ReaderType findByName(String name);

    Page<ReaderType> list(String readerTypeName, Integer status, Integer page, Integer size);

    void deleteById(Integer id);

    List<ReaderType> findAll();
}
