package cn.dsliang.library.service;

import cn.dsliang.library.entity.Rule;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RuleService {

    Rule save(Rule rule);

    Rule findById(Integer id);

    Rule findByName(String name);

    Page<Rule> list(String name, Integer status, Integer page, Integer size);

    void deleteById(Integer id);

    public List<Rule> findAll();
}
