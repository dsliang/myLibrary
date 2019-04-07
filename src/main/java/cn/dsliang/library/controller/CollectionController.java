package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Location;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.CollectionForm;
import cn.dsliang.library.from.CollectionInfoForm;
import cn.dsliang.library.service.BiblioService;
import cn.dsliang.library.service.CollectionService;
import cn.dsliang.library.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/catalog/collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    LocationService locationService;

    @Autowired
    BiblioService biblioService;

    @GetMapping("/info")
    @ResponseBody
    ApiResponse<CollectionInfoForm> getCollectionInfo(@RequestParam(name = "barcode", required = true) String barcode) {
        CollectionInfoForm form = new CollectionInfoForm();
        Collection collection = collectionService.findByBarcode(barcode);
        if (collection == null)
            throw new BusinessException(ResultEnum.COLLECTION_NOT_EXIST);

        form.setCollectionId(collection.getId());
        form.setTitle(collection.getBiblio().getTitle());
        form.setAuthor(collection.getBiblio().getAuthor());
        form.setCallNumber(collection.getCategoryNumber() + "/" + collection.getSerialNumber());
        form.setPrice(collection.getBiblio().getPrice());
        form.setPress(collection.getBiblio().getPress());

        return ApiResponse.success(form);
    }

    @GetMapping
    @ResponseBody
    ApiResponse<CollectionForm> findCollection(@RequestParam(name = "collectionId", required = true) Integer id) {
        CollectionForm form = new CollectionForm();
        Collection collection = collectionService.findById(id);
        if (collection == null)
            throw new BusinessException(ResultEnum.COLLECTION_NOT_EXIST);

        BeanUtils.copyProperties(collection, form);
        form.setCollectionId(collection.getId());
        form.setBiblioId(collection.getBiblio().getId());
        form.setLocationId(collection.getLocation().getId());
        form.setLocationName(collection.getLocation().getName());
        form.setStatusName(collection.getStatusEnum().getMessage());
        form.setCallNumber(collection.getCategoryNumber() + "/" + collection.getSerialNumber());

        return ApiResponse.success(form);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody CollectionForm collectionForm) {
        Biblio biblio = biblioService.findById(collectionForm.getBiblioId());
        if (biblio == null)
            throw new BusinessException(ResultEnum.BIBLIO_NOT_EXIST);

        Location location = locationService.findById(collectionForm.getLocationId());
        if (location == null)
            throw new BusinessException(ResultEnum.LOCATION_NOT_EXIST);

        Collection collection = new Collection();
        if (collectionForm.getCollectionId() != null) {
            collection = collectionService.findById(collectionForm.getCollectionId());
            if (collection == null)
                throw new BusinessException(ResultEnum.COLLECTION_NOT_EXIST);
        }else {
            Collection c = collectionService.findByBarcode(collection.getBarcode());
            if (c != null)
                throw new BusinessException(ResultEnum.BARCODE_IS_EXIST);

        }
        BeanUtils.copyProperties(collectionForm, collection);
        collection.setId(collectionForm.getCollectionId());
        collection.setBiblio(biblio);
        collection.setLocation(location);
        String[] s = collectionForm.getCallNumber().split("/");
        if (s.length == 0 || s.length > 2)
            throw new BusinessException(ResultEnum.CALL_NUMBER_FORMAT_ERROR);

        Integer serial = null;
        try {
            serial = s.length == 1 ? null : Integer.valueOf(s[1]);
        } catch (NumberFormatException e) {
            throw new BusinessException(ResultEnum.CALL_NUMBER_FORMAT_ERROR);
        }
        collection.setSerialNumber(serial);
        collection.setCategoryNumber(s[0]);

        collectionService.save(collection);

        return ApiResponse.success();
    }

    @PostMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<CollectionForm>> list(
            @RequestParam(required = true) Integer biblioId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<Collection> collectionPage = collectionService.list(biblioId, page - 1, size);

        List<CollectionForm> forms = new ArrayList<>();
        for (Collection collection : collectionPage.getContent()) {
            CollectionForm form = new CollectionForm();
            BeanUtils.copyProperties(collection, form);
            form.setCollectionId(collection.getId());
            form.setBiblioId(collection.getBiblio().getId());
            form.setLocationId(collection.getLocation().getId());
            form.setLocationName(collection.getLocation().getName());
            form.setStatusName(collection.getStatusEnum().getMessage());
            form.setCallNumber(collection.getCategoryNumber() + "/" + collection.getSerialNumber());

            forms.add(form);
        }

        return ApiResponse.success(new EasyuiPageResult<>(collectionPage.getTotalElements(), forms));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "collectionId", required = true) Integer id) {
        collectionService.deleteById(id);

        return ApiResponse.success();
    }
}
