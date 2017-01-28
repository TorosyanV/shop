package com.torter.service.dataservice.city;


import com.torter.service.dto.location.CitySimpleDto;

import java.util.List;

/**
 * Created by vazgen on 11/21/16.
 */
public interface CityService {
    List<CitySimpleDto> getAllByRegionId(long regionId);


}
