package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Location;
import cn.dsliang.library.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.awt.print.Pageable;

@Controller
@RequestMapping("/api/system/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    @ResponseBody
    ApiResponse<Location> findLocation(@RequestParam(name = "locationId", required = true) Integer id) {
        Location location = locationService.findById(id);
        if (location == null)
            return ApiResponse.error(" 馆藏地址不存在");
        return ApiResponse.success(location);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody Location location) {

        Location rawLocation = new Location();
        if (location.getId() != null) {
            rawLocation = locationService.findById(location.getId());
        }

        BeanUtils.copyProperties(location, rawLocation);
        locationService.save(rawLocation);

        return ApiResponse.success();
    }

    @GetMapping("/list")
    @ResponseBody
    ApiResponse<EasyuiPageResult<Location>> list(@RequestParam(required = false) String name,
                                                 @RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(name = "rows", defaultValue = "10") Integer size) {
        Page locationPage = locationService.list(name, page - 1, size);
        return ApiResponse.success(
                new EasyuiPageResult<Location>(locationPage.getTotalElements(), locationPage.getContent()));
    }

    @GetMapping("/delete")
    @ResponseBody
    ApiResponse delete(@RequestParam(name = "locationId", required = true) Integer id) {
        locationService.deleteById(id);
        return ApiResponse.success();
    }
}
