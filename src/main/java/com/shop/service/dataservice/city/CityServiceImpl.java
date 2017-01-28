package com.shop.service.dataservice.city;


import com.shop.data.repository.CityRepository;
import com.shop.data.entity.CityEntity;
import com.shop.helper.DozerExtension;
import com.shop.service.dto.location.CitySimpleDto;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vazgen on 11/21/16.
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CitySimpleDto> getAllByRegionId(long regionId) {
        List<CityEntity> cityEntities = cityRepository.findByRegionIdOrderByNameAsc(regionId);

        List<CitySimpleDto> citySimpleDtos = DozerExtension.map(mapper, cityEntities, CitySimpleDto.class);
        return citySimpleDtos;
    }
}
