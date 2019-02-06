package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.ReaderType;
import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.from.ReaderTypeForm;
import cn.dsliang.library.service.ReaderTypeService;
import cn.dsliang.library.service.RuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/system/readerType")
public class ReaderTypeController {

    @Autowired
    private ReaderTypeService readerTypeService;

    @Autowired
    private RuleService ruleService;

    @GetMapping
    @ResponseBody
    ApiResponse<ReaderType> findReaderType(@RequestParam(name = "readerTypeId", required = true) Integer id) {
        ReaderType readerType = readerTypeService.findById(id);
        if (readerType == null) {
            return ApiResponse.error("读者类型不存在");
        }
        return ApiResponse.success(readerType);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody ReaderTypeForm readerTypeForm) {
        Rule rule = ruleService.findById(readerTypeForm.getRuleId());
        if (rule == null)
            return ApiResponse.error("借阅规则不存在");

        ReaderType rawReaderType = new ReaderType();
        BeanUtils.copyProperties(readerTypeForm, rawReaderType);
        rawReaderType.setRule(rule);
        readerTypeService.save(rawReaderType);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<ReaderType>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<ReaderType> readerTypePage = readerTypeService.list(page - 1, size);
        return ApiResponse.success(
                new EasyuiPageResult<ReaderType>(readerTypePage.getTotalElements(), readerTypePage.getContent()));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "readerTypeId", required = true) Integer id) {
        readerTypeService.deleteById(id);
        return ApiResponse.success();
    }

}
