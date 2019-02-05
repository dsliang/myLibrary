package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.repository.BiblioRepository;
import cn.dsliang.library.service.BiblioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BiblioServiceImpl implements BiblioService {

    @Autowired
    BiblioRepository biblioRepository;

    @Override
    public Biblio save(Biblio biblio) {
        return biblioRepository.save(biblio);
    }

    @Override
    public Biblio findById(Integer id) {
        return biblioRepository.findOne(id);
    }

    @Override
    public Page<Biblio> list(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return biblioRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        biblioRepository.delete(id);
    }
}
