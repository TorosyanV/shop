package com.shop.service.dataservice.product;

import com.shop.data.entity.ProductPageViewEntity;
import com.shop.data.repository.ProductPageViewRepository;
import com.shop.service.analytic.dto.DayCountPair;
import com.shop.service.dataservice.user.UserService;
import com.shop.service.dto.product.ProductViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhirayrg on 3/10/2017.
 */
@Service
public class ProductPageViewServiceImpl implements ProductPageViewService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPageViewRepository productPageViewRepository;

    @Override
    @Transactional
    public void addView(ProductViewDto viewDto) {

        ProductPageViewEntity viewEntity = new ProductPageViewEntity();

        if (viewDto.getUserId().isPresent()) {
            viewEntity.setUser(userService.getById(viewDto.getUserId().get()));
        }

        viewEntity.setProduct(productService.getById(viewDto.getProductId()));
        viewEntity.setReferral(viewDto.getReferral());

        productPageViewRepository.save(viewEntity);

    }

    @Override
    public List<DayCountPair> getViewHistory(long productId) {


        List<ProductPageViewEntity> allByProductId = productPageViewRepository.findAllByProductId(productId);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        List<DayCountPair> viewHistory = allByProductId.stream()
                .collect(Collectors.groupingBy(e -> df.format(e.getViewDate()),
                        Collectors.counting())).entrySet().stream()
                .map(x -> new DayCountPair(x.getKey(), x.getValue())).collect(Collectors.toList());

        return viewHistory;
    }
}
