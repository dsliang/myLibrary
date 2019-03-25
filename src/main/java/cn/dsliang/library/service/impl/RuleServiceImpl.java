package cn.dsliang.library.service.impl;

import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.repository.RuleRepository;
import cn.dsliang.library.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<Rule> list(String name, Integer status, Integer page, Integer size) {
        Rule rule = new Rule();
        rule.setName(name);
        rule.setStatus(status);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Rule> example = Example.of(rule, matcher);
        Pageable pageable = new PageRequest(page, size);

        return ruleRepository.findAll(example, pageable);
    }

    @Override
    public void deleteById(Integer id) {
        ruleRepository.delete(id);
    }

    @Override
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }
}
