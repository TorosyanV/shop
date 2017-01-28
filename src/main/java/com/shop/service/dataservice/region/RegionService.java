package com.shop.service.dataservice.region;


import com.shop.service.dto.location.RegionSimpleDto;

import java.util.List;

/**
 * Created by vazgen on 11/15/16.
 */
public interface RegionService {

    List<RegionSimpleDto> getAll();
}
