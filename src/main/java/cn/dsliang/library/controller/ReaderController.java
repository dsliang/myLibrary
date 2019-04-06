package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Circulating;
import cn.dsliang.library.entity.Reader;
import cn.dsliang.library.entity.ReaderType;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.ReaderForm;
import cn.dsliang.library.from.ReaderInfoForm;
import cn.dsliang.library.service.BorrowService;
import cn.dsliang.library.service.ReaderService;
import cn.dsliang.library.service.ReaderTypeService;
import cn.dsliang.library.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/circulation/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private ReaderTypeService readerTypeService;

    @Autowired
    private BorrowService borrowService;

    private Integer calculateExtendedNumber(List<Circulating> circulatings) {
        Integer num = 0;
        if (circulatings == null || circulatings.isEmpty())
            return num;

        for (Circulating circulating : circulatings) {
            if (DateUtil.compareDate(DateUtil.getCurrentDate(), circulating.getReturnDate()) != -1)
                num++;
        }

        return num;
    }


    @GetMapping("/info")
    @ResponseBody
    ApiResponse<ReaderInfoForm> getReaderInfo(@RequestParam(name = "card", required = true) String card) {
        ReaderInfoForm form = new ReaderInfoForm();
        Reader reader = readerService.findByCard(card);
        if (reader == null)
            throw new BusinessException(ResultEnum.READER_NOT_EXIST);
        List<Circulating> circulatings = borrowService.getCirculating(reader.getId());

        form.setReaderId(reader.getId());
        form.setName(reader.getName());
        form.setStatusName(reader.getStatusEnum().getMessage());
        form.setBorrowNumber(reader.getReaderType().getRule().getBorrowNumber());
        form.setBorrowedNumber(circulatings.size());
        form.setExtendedNumber(calculateExtendedNumber(circulatings));

        return ApiResponse.success(form);
    }

    @GetMapping
    @ResponseBody
    ApiResponse<ReaderForm> findReader(@RequestParam(name = "readerId", required = true) Integer id) {
        ReaderForm form = new ReaderForm();
        Reader reader = readerService.findById(id);

        if (reader == null)
            throw new BusinessException(ResultEnum.READER_NOT_EXIST);

        copyProperties(reader, form);

        return ApiResponse.success(form);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody ReaderForm readerForm) {
        ReaderType readerType = readerTypeService.findById(readerForm.getReaderTypeId());
        if (readerType == null)
            throw new BusinessException(ResultEnum.READER_TYPE_NOT_EXIST);

        Reader reader = new Reader();
        if (readerForm.getReaderId() != null) {
            reader = readerService.findById(readerForm.getReaderId());
            if (reader == null)
                throw new BusinessException(ResultEnum.READER_NOT_EXIST);
        }

        BeanUtils.copyProperties(readerForm, reader);
        reader.setId(readerForm.getReaderId());
        reader.setCard(readerForm.getReaderCard());
        reader.setName(readerForm.getReaderName());
        reader.setReaderType(readerType);

        Reader r = readerService.findByCard(reader.getCard());
        if (r != null)
            throw new BusinessException(ResultEnum.CARD_IS_EXIST);

        readerService.save(reader);

        return ApiResponse.success();
    }

    @PostMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<ReaderForm>> list(
            String readerName,
            String readerCard,
            Integer status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        List<ReaderForm> readerForms = new ArrayList<>();
        Page<Reader> collectionPage = readerService.list(readerName, readerCard, status, page - 1, size);
        for (Reader reader : collectionPage.getContent()) {
            ReaderForm form = new ReaderForm();

            copyProperties(reader, form);

            readerForms.add(form);
        }

        return ApiResponse.success(new EasyuiPageResult<ReaderForm>(collectionPage.getTotalElements(), readerForms));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "readerId", required = true) Integer id) {
        readerService.deleteById(id);

        return ApiResponse.success();
    }

    private void copyProperties(Reader reader, ReaderForm readerForm) {
        readerForm.setReaderId(reader.getId());
        readerForm.setReaderCard(reader.getCard());
        readerForm.setReaderName(reader.getName());
        readerForm.setGender(reader.getGender());
        readerForm.setGenderName(reader.getGenderEnum().getMessage());
        readerForm.setReaderTypeId(reader.getReaderType().getId());
        readerForm.setReaderTypeName(reader.getReaderType().getName());
        readerForm.setStatus(reader.getStatus());
        readerForm.setStatusName(reader.getStatusEnum().getMessage());
    }
}
