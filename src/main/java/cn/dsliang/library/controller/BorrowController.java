package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Circulating;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Reader;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.from.BorrowRecordForm;
import cn.dsliang.library.service.BorrowService;
import cn.dsliang.library.service.CollectionService;
import cn.dsliang.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            return ApiResponse.error(ResultEnum.READER_NOT_EXIST.getMessage());

        Collection collection = collectionService.findById(collectionId);
        if (collection == null)
            return ApiResponse.error(ResultEnum.COLLECTION_NOT_EXIST.getMessage());

        borrowService.borrow(reader, collection);

        return ApiResponse.success();
    }

    @PostMapping("/records")
    @ResponseBody
    ApiResponse<EasyuiPageResult<BorrowRecordForm>> getBorrowedRecords(@RequestParam(required = true) Integer readerId,
                                                                       @RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Reader reader = readerService.findById(readerId);
        if (reader == null)
            return ApiResponse.error(ResultEnum.READER_NOT_EXIST.getMessage());

        List<BorrowRecordForm> forms = new ArrayList<>();
        Page<Circulating> circulatingPage = borrowService.getCirculating(readerId, page - 1, size);
        for (Circulating circulating : circulatingPage.getContent()) {
            BorrowRecordForm form = new BorrowRecordForm();
            form.setCollectionId(circulating.getCollection().getId());
            form.setTitle(circulating.getBiblio().getTitle());
            form.setCallNumber(circulating.getCollection().getCategoryNumber() + "/" + circulating.getCollection().getSerialNumber());
            form.setLocationName(circulating.getCollection().getLocation().getName());
            form.setBorrowDate(circulating.getBorrowDate());
            form.setReturnDate(circulating.getReturnDate());

            forms.add(form);
        }

        return ApiResponse.success(new EasyuiPageResult<>(circulatingPage.getTotalElements(), forms));
    }
}
