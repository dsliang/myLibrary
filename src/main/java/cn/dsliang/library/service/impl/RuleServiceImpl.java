package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.repository.RuleRepository;
import cn.dsliang.library.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    RuleRepository ruleRepository;

    @Override
    public Rule save(Rule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public Rule findById(Integer id) {
        return ruleRepository.findOne(id);
    }

    @Override
    public Page<Rule> list(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        return ruleRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        ruleRepository.delete(id);
    }
}
