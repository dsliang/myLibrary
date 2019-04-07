package cn.dsliang.library.controller;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.common.EasyuiPageResult;
import cn.dsliang.library.entity.Location;
import cn.dsliang.library.enums.ResultEnum;
import cn.dsliang.library.exception.BusinessException;
import cn.dsliang.library.from.OptionForm;
import cn.dsliang.library.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
            throw new BusinessException(ResultEnum.LOCATION_NOT_EXIST);

        return ApiResponse.success(location);
    }

    @PostMapping("/save")
    @ResponseBody
    ApiResponse save(@RequestBody Location location) {
        Location rawLocation = new Location();
        if (location.getId() != null) {
            rawLocation = locationService.findById(location.getId());
            if (rawLocation == null)
                throw new BusinessException(ResultEnum.LOCATION_NOT_EXIST);
        } else {
            Location l = locationService.findByName(location.getName());
            if (l != null)
                throw new BusinessException(ResultEnum.LOCATION_IS_EXIST);
        }

        BeanUtils.copyProperties(location, rawLocation);


        locationService.save(rawLocation);

        return ApiResponse.success();
    }

    @PostMapping("/list")
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

    @GetMapping("/option")
    @ResponseBody
    ApiResponse<List<OptionForm>> option() {
        List<OptionForm> options = new ArrayList<>();
        List<Location> locations = locationService.findAll();
        for (Location location : locations) {
            OptionForm option = new OptionForm();
            option.setId(location.getId());
            option.setText(location.getName());

            options.add(option);
        }

        return ApiResponse.success(options);
    }
}
