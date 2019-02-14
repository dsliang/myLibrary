package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Reader;
import cn.dsliang.library.repository.ReaderRepository;
import cn.dsliang.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    public Page<Reader> list(String readerName, Integer status, Integer page, Integer size) {
        Reader reader = new Reader();
        reader.setName(readerName);
        reader.setStatus(status);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Reader> example = Example.of(reader, matcher);
        Pageable pageable = new PageRequest(page, size);

        return readerRepository.findAll(example,pageable);
    }

    @Override
    public void deleteById(Integer id) {
        readerRepository.delete(id);
    }
}
