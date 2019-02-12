package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Reader;
import cn.dsliang.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/circulation/return")
public class ReturnController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    ReturnService returnService;

    @GetMapping
    @ResponseBody
    ApiResponse returns(@RequestParam(required = true) Integer collectionId) {
        Collection collection = collectionService.findById(collectionId);
        if (collection == null)
            return ApiResponse.error("馆藏不存在");
        returnService.returns(collection);
        return ApiResponse.success();
    }
}
