package com.shop.service.dataservice.composition;


import com.shop.service.dto.CompositionSimpleDto;

import java.util.List;

/**
 * Created by Vazgen on 08/17/2016.
 */
public interface CompositionService {

    List<CompositionSimpleDto> getAll();
}
