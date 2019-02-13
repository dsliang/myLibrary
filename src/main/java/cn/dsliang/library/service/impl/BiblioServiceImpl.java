package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.repository.BiblioRepository;
import cn.dsliang.library.service.BiblioService;
import cn.dsliang.library.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
    public Page<Biblio> list(String titleOrIsbn, Integer page, Integer size) {
        return biblioRepository.findAll(new Specification<Biblio>() {
            @Override
            public Predicate toPredicate(Root<Biblio> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (StringUtil.isNotEmpty(titleOrIsbn)) {
                    Predicate predicate = cb.or(cb.like(root.get("title").as(String.class), "%" + titleOrIsbn + "%"),
                            cb.like(root.get("isbn").as(String.class), "%" + titleOrIsbn + "%"));
                    predicates.add(predicate);
                }

                Predicate[] pre = new Predicate[predicates.size()];
                return query.where(predicates.toArray(pre)).getRestriction();
            }
        }, new PageRequest(page, size));
    }

    @Override
    public void deleteById(Integer id) {
        biblioRepository.delete(id);
    }
}
