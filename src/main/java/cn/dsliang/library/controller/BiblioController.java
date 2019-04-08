package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.BiblioForm;
import cn.dsliang.library.service.BiblioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/catalog/biblio")
public class BiblioController {

    @Autowired
    private BiblioService biblioService;

    @GetMapping
    @ResponseBody
    ApiResponse<BiblioForm> findBiblio(@RequestParam(name = "biblioId", required = true) Integer id) {
        BiblioForm form = new BiblioForm();
        Biblio biblio = biblioService.findById(id);
        if (biblio == null)
            throw new BusinessException(ResultEnum.BIBLIO_NOT_EXIST);

        BeanUtils.copyProperties(biblio, form);
        List<Integer> list = new ArrayList<>();
        for (Collection collection : biblio.getCollections()) {
            list.add(collection.getSerialNumber());
        }
        form.setSerialNumbers(list.stream().distinct().collect(Collectors.toList()));

        return ApiResponse.success(form);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody BiblioForm form) {
        Biblio rawBiblio = new Biblio();
        if (form.getId() != null) {
            rawBiblio = biblioService.findById(form.getId());
            if (rawBiblio == null)
                throw new BusinessException(ResultEnum.RULE_NOT_EXIST);
        }

        BeanUtils.copyProperties(form, rawBiblio);

        biblioService.save(rawBiblio);

        return ApiResponse.success();
    }

    @PostMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<BiblioForm>> list(
            @RequestParam(required = false) String titleOrIsbn,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        List<BiblioForm> forms = new ArrayList<>();
        Page<Biblio> biblioPage = biblioService.list(titleOrIsbn, page - 1, size);
        for (Biblio biblio : biblioPage.getContent()) {
            BiblioForm form = new BiblioForm();
            BeanUtils.copyProperties(biblio, form);
            List<Integer> list = new ArrayList<>();
            for (Collection collection : biblio.getCollections()) {
                list.add(collection.getSerialNumber());
            }
            form.setSerialNumbers(list.stream().distinct().collect(Collectors.toList()));

            forms.add(form);
        }

        return ApiResponse.success(
                new EasyuiPageResult<BiblioForm>(biblioPage.getTotalElements(), forms));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "biblioId", required = true) Integer id) {
        biblioService.deleteById(id);

        return ApiResponse.success();
    }

}
