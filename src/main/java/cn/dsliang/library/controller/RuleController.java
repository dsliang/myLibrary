package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.entity.User;
import cn.dsliang.library.service.RuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/system/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @GetMapping
    @ResponseBody
    ApiResponse<Rule> findRule(@RequestParam(name = "ruleId", required = true) Integer id) {
        Rule rule = ruleService.findById(id);
        if (rule == null) {
            return ApiResponse.error("借阅规则不存在");
        }
        return ApiResponse.success(rule);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody Rule rule) {
        Rule rawRule = new Rule();
        if (rule.getRuleId() != null) {
            rawRule = ruleService.findById(rule.getRuleId());
            if (rawRule == null)
                return ApiResponse.error("借阅规则不存在");
        }

        BeanUtils.copyProperties(rule, rawRule);
        ruleService.save(rawRule);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<Rule>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<Rule> rulePage = ruleService.list(page - 1, size);
        return ApiResponse.success(
                new EasyuiPageResult<Rule>(rulePage.getTotalElements(), rulePage.getContent()));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "ruleId", required = true) Integer id) {
        ruleService.deleteById(id);
        return ApiResponse.success();
    }

}