package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Rule;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.service.BiblioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

@Controller
@RequestMapping("/api/catalog/biblio")
public class BiblioController {

    @Autowired
    private BiblioService biblioService;

    @GetMapping
    @ResponseBody
    ApiResponse<Biblio> findBiblio(@RequestParam(name = "biblioId", required = true) Integer id) {
        Biblio biblio = biblioService.findById(id);
        if (biblio == null)
            throw new BusinessException(ResultEnum.BIBLIO_NOT_EXIST);

        return ApiResponse.success(biblio);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody Biblio biblio) {
        Biblio rawBiblio = new Biblio();
        if (biblio.getId() != null) {
            rawBiblio = biblioService.findById(biblio.getId());
            if (rawBiblio == null)
                throw new BusinessException(ResultEnum.RULE_NOT_EXIST);
        }

        BeanUtils.copyProperties(biblio, rawBiblio);
        biblioService.save(rawBiblio);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<Biblio>> list(
            @RequestParam(required = false) String titleOrIsbn,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<Biblio> biblioPage = biblioService.list(titleOrIsbn, page - 1, size);
        return ApiResponse.success(
                new EasyuiPageResult<Biblio>(biblioPage.getTotalElements(), biblioPage.getContent()));

    }


    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "biblioId", required = true) Integer id) {
        biblioService.deleteById(id);
        return ApiResponse.success();
    }

}
