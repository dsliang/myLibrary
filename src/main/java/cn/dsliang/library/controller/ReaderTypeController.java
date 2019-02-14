package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.ReaderType;
import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.ReaderTypeForm;
import cn.dsliang.library.service.ReaderTypeService;
import cn.dsliang.library.service.RuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

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
        if (readerType == null)
            throw new BusinessException(ResultEnum.READER_TYPE_NOT_EXIST);

        return ApiResponse.success(readerType);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody ReaderTypeForm readerTypeForm) {
        Rule rule = ruleService.findById(readerTypeForm.getRuleId());
        if (rule == null)
            throw new BusinessException(ResultEnum.RULE_NOT_EXIST);

        ReaderType readerType = new ReaderType();
        if (readerTypeForm.getReaderTypeId() != null) {
            readerType = readerTypeService.findById(readerTypeForm.getReaderTypeId());
            if (readerType == null)
                throw new BusinessException(ResultEnum.READER_TYPE_NOT_EXIST);
        }
        BeanUtils.copyProperties(readerTypeForm, readerType);
        readerType.setName(readerTypeForm.getReaderTypeName());
        readerType.setRule(rule);
        readerTypeService.save(readerType);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<ReaderType>> list(
            String readerTypeName,
            Integer status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<ReaderType> readerTypePage = readerTypeService.list(readerTypeName,status,page - 1, size);
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
