package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.ReaderType;
import cn.dsliang.library.repository.ReaderTypeRepository;
import cn.dsliang.library.service.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ReaderType findByName(String name) {
        return readerTypeRepository.findByName(name);
    }

    @Override
    public Page<ReaderType> list(String readerTypeName, Integer status, Integer page, Integer size) {
        ReaderType readerType = new ReaderType();
        readerType.setName(readerTypeName);
        readerType.setStatus(status);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<ReaderType> example = Example.of(readerType, matcher);
        Pageable pageable = new PageRequest(page, size);

        return readerTypeRepository.findAll(example, pageable);
    }

    @Override
    public void deleteById(Integer id) {
        readerTypeRepository.delete(id);
    }

    @Override
    public List<ReaderType> findAll() {
        return readerTypeRepository.findAll();
    }

    @Override
    public Integer countByRuleId(Integer id) {
        return readerTypeRepository.countByRuleId(id);
    }
}
