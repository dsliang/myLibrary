package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Biblio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

@Controller
@RequestMapping("/api/catalog/biblio")
public class BiblioController {

    @GetMapping
    @ResponseBody
    ApiResponse<Biblio> findBiblio(@RequestParam(name = "biblioId", required = true) Integer id) {

        return null;
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody Biblio biblio) {

        return null;
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<Biblio>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer size) {

        return null;
    }


    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "biblioId", required = true) Integer id) {

        return null;
    }

}
