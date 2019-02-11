package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.entity.Circulating;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Reader;
import cn.dsliang.library.repository.CirculatingRepository;
import cn.dsliang.library.service.BorrowService;
import cn.dsliang.library.service.CollectionService;
import cn.dsliang.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Id;
import java.util.List;

@Controller
@RequestMapping("/api/circulation/borrow")
public class BorrowController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    ReaderService readerService;

    @Autowired
    BorrowService borrowService;

    @GetMapping
    @ResponseBody
    ApiResponse borrow(@RequestParam(required = true) Integer readerId,
                       @RequestParam(required = true) Integer collectionId) {
        Reader reader = readerService.findById(readerId);
        if (reader == null)
            return ApiResponse.error("读者不存在");
        Collection collection = collectionService.findById(collectionId);
        if (collection == null)
            return ApiResponse.error("馆藏不存在");
        borrowService.borrow(reader, collection);
        return ApiResponse.success();
    }
}
