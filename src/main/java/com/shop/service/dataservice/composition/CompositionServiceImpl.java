package com.shop.service.dataservice.composition;

import com.shop.data.repository.CompositionRepository;
import com.shop.data.entity.CompositionEntity;
import com.shop.helper.DozerExtension;
import com.shop.service.dto.CompositionSimpleDto;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by vazgen on 11/7/16.
 */

@Service
public class CompositionServiceImpl implements CompositionService {


    @Autowired
    private Mapper mapper;
    @Autowired
    private CompositionRepository equipmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CompositionSimpleDto> getAll() {

        List<CompositionEntity> equipmentEntities = equipmentRepository.findAll();

        List<CompositionSimpleDto> equipments = DozerExtension.map(mapper, equipmentEntities, CompositionSimpleDto.class);
        return equipments;
    }
}
