package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Biblio;
import cn.dsliang.library.entity.Collection;
import cn.dsliang.library.entity.Location;
import cn.dsliang.library.from.CollectionForm;
import cn.dsliang.library.service.BiblioService;
import cn.dsliang.library.service.CollectionService;
import cn.dsliang.library.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            return ApiResponse.error("馆藏不存在");
        return ApiResponse.success(collection);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody CollectionForm collectionForm) {
        Biblio biblio = biblioService.findById(collectionForm.getBiblioId());
        if (biblio == null)
            return ApiResponse.error("书目不存在");
        Location location = locationService.findById(collectionForm.getLocationId());
        if (location == null)
            return ApiResponse.error("馆藏地址不存在");

        Collection collection = new Collection();
        BeanUtils.copyProperties(collectionForm, collection, "status");
        collection.setBiblio(biblio);
        collection.setLocation(location);
        collectionService.save(collection);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<Collection>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page<Collection> collectionPage = collectionService.list(page - 1, size);
        return ApiResponse.success(new EasyuiPageResult<>(collectionPage.getTotalElements(), collectionPage.getContent()));
    }

    ApiResponse delete(@RequestParam(name = "collectionId", required = true) Integer id) {
        collectionService.deleteById(id);
        return ApiResponse.success();
    }
}
