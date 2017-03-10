package com.shop.web.api;

import com.shop.service.dataservice.city.CityService;
import com.shop.service.dataservice.region.RegionService;
import com.shop.service.dto.location.CitySimpleDto;
import com.shop.service.dto.location.RegionSimpleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping( value = "/regions",method = RequestMethod.GET)
    public List<RegionSimpleDto> getRegions() {

        List<RegionSimpleDto> regionSimpleDtos = regionService.getAll();
        return regionSimpleDtos;
    }

    @RequestMapping(value = "regions/{id}/cities", method = RequestMethod.GET)
    public List<CitySimpleDto> getCities(@PathVariable("id") Long regionId) {

        return cityService.getAllByRegionId(regionId);
    }
}
