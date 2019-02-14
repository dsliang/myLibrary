package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Location;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.CollectionForm;
import cn.dsliang.library.service.BiblioService;
import cn.dsliang.library.service.CollectionService;
import cn.dsliang.library.service.LocationService;
import com.sun.org.apache.regexp.internal.RE;
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

    @GetMapping
    @ResponseBody
    ApiResponse<Collection> findCollection(@RequestParam(name = "collectionId", required = true) Integer id) {
        Collection collection = collectionService.findById(id);
        if (collection == null)
            throw new BusinessException(ResultEnum.COLLECTION_NOT_EXIST);

        return ApiResponse.success(collection);
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
        }
        BeanUtils.copyProperties(collectionForm, collection);
        collection.setId(collectionForm.getCollectionId());
        collection.setBiblio(biblio);
        collection.setLocation(location);
        collectionService.save(collection);

        return ApiResponse.success();
    }

    @GetMapping("/list")
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
