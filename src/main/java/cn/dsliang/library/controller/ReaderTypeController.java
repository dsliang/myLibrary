package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.ReaderType;
import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.OptionForm;
import cn.dsliang.library.from.ReaderTypeForm;
import cn.dsliang.library.service.ReaderService;
import cn.dsliang.library.service.ReaderTypeService;
import cn.dsliang.library.service.RuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/system/readerType")
public class ReaderTypeController {

    @Autowired
    private ReaderTypeService readerTypeService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private ReaderService readerService;

    @GetMapping
    @ResponseBody
    ApiResponse<ReaderTypeForm> findReaderType(@RequestParam(name = "readerTypeId", required = true) Integer id) {
        ReaderTypeForm typeForm = new ReaderTypeForm();
        ReaderType type = readerTypeService.findById(id);
        if (type == null)
            throw new BusinessException(ResultEnum.READER_TYPE_NOT_EXIST);
        copyProperties(type, typeForm);

        return ApiResponse.success(typeForm);
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
        } else {
            ReaderType r = readerTypeService.findByName(readerType.getName());
            if (r != null)
                throw new BusinessException(ResultEnum.READER_TYPE_IS_EXIST);

        }
        BeanUtils.copyProperties(readerTypeForm, readerType);
        readerType.setId(readerTypeForm.getReaderTypeId());
        readerType.setName(readerTypeForm.getReaderTypeName());
        readerType.setRule(rule);

        readerTypeService.save(readerType);

        return ApiResponse.success();
    }

    @PostMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<ReaderTypeForm>> list(
            String readerTypeName,
            Integer status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        List<ReaderTypeForm> typeForms = new ArrayList<>();
        Page<ReaderType> readerTypePage = readerTypeService.list(readerTypeName, status, page - 1, size);
        for (ReaderType type : readerTypePage.getContent()) {
            ReaderTypeForm form = new ReaderTypeForm();
            copyProperties(type, form);

            typeForms.add(form);
        }

        return ApiResponse.success(
                new EasyuiPageResult<ReaderTypeForm>(readerTypePage.getTotalElements(), typeForms));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "readerTypeId", required = true) Integer id) {
        Integer count = readerService.countByReaderTypeId(id);
        if (count > 0)
            throw new BusinessException(ResultEnum.READER_TYPE_IN_USE);

        readerTypeService.deleteById(id);

        return ApiResponse.success();
    }

    @GetMapping("/option")
    @ResponseBody
    ApiResponse<List<OptionForm>> option() {
        List<OptionForm> options = new ArrayList<>();
        List<ReaderType> readerTypes = readerTypeService.findAll();
        for (ReaderType readerType : readerTypes) {
            OptionForm option = new OptionForm();
            option.setId(readerType.getId());
            option.setText(readerType.getName());

            options.add(option);
        }

        return ApiResponse.success(options);
    }

    private void copyProperties(ReaderType type, ReaderTypeForm typeForm) {
        BeanUtils.copyProperties(type, typeForm);
        typeForm.setReaderTypeId(type.getId());
        typeForm.setReaderTypeName(type.getName());
        typeForm.setRuleId(type.getRule().getId());
        typeForm.setRuleName(type.getRule().getName());
        typeForm.setStatusName(type.getStatusEnum().getMessage());
    }
}
