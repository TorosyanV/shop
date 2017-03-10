package com.shop.web.api;

import com.shop.service.dataservice.city.CityService;
import com.shop.service.dataservice.region.RegionService;
import com.shop.service.dto.location.CitySimpleDto;
import com.shop.service.dto.location.RegionSimpleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhirayrg on 3/9/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class RegionController {


    @Autowired
    private RegionService regionService;
    @Autowired
    private CityService cityService;
    @GetMapping( value = "/regions")
    public List<RegionSimpleDto> getRegions() {

        List<RegionSimpleDto> regionSimpleDtos = regionService.getAll();
        return regionSimpleDtos;
    }

    @GetMapping(value = "regions/{id}/cities")
    public List<CitySimpleDto> getCities(@PathVariable("id") Long regionId) {

        return cityService.getAllByRegionId(regionId);
    }
}
