package com.shop.service.dataservice.region;

import com.shop.data.entity.RegionEntity;
import com.shop.data.repository.RegionRepository;
import com.shop.helper.DozerExtension;
import com.shop.service.dto.location.RegionSimpleDto;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vazgen on 11/15/16.
 */
@Service
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;
    private Mapper mapper;

    @Autowired
    RegionServiceImpl(RegionRepository regionRepository, Mapper mapper){
        this.regionRepository=regionRepository;
        this.mapper=mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegionSimpleDto> getAll() {

        List<RegionEntity> regionEntities = regionRepository.findAll(new Sort(Sort.Direction.ASC, "name"));

        List<RegionSimpleDto> regionSimpleDtos = DozerExtension.map(mapper, regionEntities, RegionSimpleDto.class);
        return regionSimpleDtos;
    }
}
