package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.OptionForm;
import cn.dsliang.library.service.RuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/system/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @GetMapping
    @ResponseBody
    ApiResponse<Rule> findRule(@RequestParam(name = "ruleId", required = true) Integer id) {
        Rule rule = ruleService.findById(id);
        if (rule == null)
            throw new BusinessException(ResultEnum.RULE_NOT_EXIST);

        return ApiResponse.success(rule);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody Rule rule) {
        Rule rawRule = new Rule();
        if (rule.getId() != null) {
            rawRule = ruleService.findById(rule.getId());
            if (rawRule == null)
                throw new BusinessException(ResultEnum.RULE_NOT_EXIST);
        }
        BeanUtils.copyProperties(rule, rawRule);

        Rule r = ruleService.findByName(rule.getName());
        if (r != null)
            throw new BusinessException(ResultEnum.RULE_IS_EXIST);

        ruleService.save(rawRule);

        return ApiResponse.success();
    }

    @PostMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<Rule>> list(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) Integer status,
                                             @RequestParam(defaultValue = "1") Integer page,
                                             @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<Rule> rulePage = ruleService.list(name, status, page - 1, size);
        return ApiResponse.success(
                new EasyuiPageResult<Rule>(rulePage.getTotalElements(), rulePage.getContent()));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "ruleId", required = true) Integer id) {
        ruleService.deleteById(id);

        return ApiResponse.success();
    }

    @GetMapping("/option")
    @ResponseBody
    ApiResponse<List<OptionForm>> option() {
        List<OptionForm> options = new ArrayList<>();
        List<Rule> rules = ruleService.findAll();
        for (Rule rule : rules) {
            OptionForm option = new OptionForm();
            option.setId(rule.getId());
            option.setText(rule.getName());

            options.add(option);
        }

        return ApiResponse.success(options);
    }

}
