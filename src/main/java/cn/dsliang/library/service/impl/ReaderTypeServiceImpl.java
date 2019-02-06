package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.ReaderType;
import cn.dsliang.library.repository.ReaderRepository;
import cn.dsliang.library.repository.ReaderTypeRepository;
import cn.dsliang.library.service.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReaderTypeServiceImpl implements ReaderTypeService {

    @Autowired
    ReaderTypeRepository readerTypeRepository;

    @Override
    public ReaderType save(ReaderType readerType) {
        return readerTypeRepository.save(readerType);
    }

    @Override
    public ReaderType findById(Integer id) {
        return readerTypeRepository.findOne(id);
    }

    @Override
    public Page<ReaderType> list(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return readerTypeRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        readerTypeRepository.delete(id);
    }
}
