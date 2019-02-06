package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Reader;
import cn.dsliang.library.repository.ReaderRepository;
import cn.dsliang.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReaderServiceImpl implements ReaderService {


    @Autowired
    ReaderRepository readerRepository;

    @Override
    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Reader findById(Integer id) {
        return readerRepository.findOne(id);
    }

    @Override
    public Page<Reader> list(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return readerRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        readerRepository.delete(id);
    }
}
