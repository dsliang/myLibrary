package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.service.CollectionService;
import cn.dsliang.library.service.RenewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/circulation/renew")
public class RenewController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    RenewService renewService;

    @GetMapping
    @ResponseBody
    ApiResponse renew(@RequestParam(required = true) Integer collectionId) {
        Collection collection = collectionService.findById(collectionId);
        if (collection == null)
            return ApiResponse.error(ResultEnum.COLLECTION_NOT_EXIST.getMessage());

        renewService.renew(collection);

        return ApiResponse.success();
    }
}
