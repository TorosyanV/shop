package com.torter.service.dataservice.composition;

import com.torter.data.entity.CompositionEntity;
import com.torter.data.repository.CompositionRepository;
import com.torter.service.dto.CompositionSimpleDto;
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

        List<CompositionSimpleDto> equipments = com.torter.helper.DozerExtension.map(mapper, equipmentEntities, CompositionSimpleDto.class);
        return equipments;
    }
}
