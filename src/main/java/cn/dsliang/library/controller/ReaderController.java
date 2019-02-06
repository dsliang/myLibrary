package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.*;
import cn.dsliang.library.from.CollectionForm;
import cn.dsliang.library.from.ReaderForm;
import cn.dsliang.library.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/circulation/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderTypeService readerTypeService;

    @GetMapping
    @ResponseBody
    ApiResponse<Reader> findReader(@RequestParam(name = "readerId", required = true) Integer id) {
        Reader reader = readerService.findById(id);
        if (reader == null)
            return ApiResponse.error("读者不存在");
        return ApiResponse.success(reader);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody ReaderForm readerForm) {
        ReaderType readerType = readerTypeService.findById(readerForm.getReaderTypeId());
        if (readerType == null)
            return ApiResponse.error("读者类型不存在");

        Reader reader = new Reader();
        BeanUtils.copyProperties(readerForm, reader);
        reader.setReaderType(readerType);
        readerService.save(reader);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<Reader>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<Reader> collectionPage = readerService.list(page - 1, size);
        return ApiResponse.success(new EasyuiPageResult<Reader>(collectionPage.getTotalElements(), collectionPage.getContent()));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "readerId", required = true) Integer id) {
        readerService.deleteById(id);
        return ApiResponse.success();
    }
}
