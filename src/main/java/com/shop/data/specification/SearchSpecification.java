package com.shop.data.specification;


import com.shop.data.entity.ProductEntity;
import com.shop.data.specification.filter.SearchFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Zhirayr.Gumruyan on 12/6/2016.
 */
public class SearchSpecification implements Specification<ProductEntity> {


    private SearchFilter filter;

    public SearchSpecification(SearchFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

        final Collection<Predicate> predicates = new ArrayList<>();

        if (filter.getCityId() != null) {
            predicates.add(cb.and(cb.equal(root.<Long>get("city").get("id"), filter.getCityId())));
        } else if (filter.getRegionId() != null) {
            predicates.add(cb.and(cb.equal(root.<Long>get("city").get("region").get("id"), filter.getRegionId())));
        } else if (filter.getCountryId() != null) {
            predicates.add(cb.and(cb.equal(root.<Long>get("city").get("region").get("country").get("id"), filter.getCountryId())));
        }


        if (filter.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.<Double>get("detail").get("price"), filter.getMaxPrice()));
        }

        if (filter.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.<Double>get("detail").get("price"), filter.getMinPrice()));
        }

        if (filter.getWithImage() != null && filter.getWithImage()) {
            predicates.add(cb.isNotNull(root.get("mainImage")));
        }

        predicates.add(cb.equal(root.<Boolean>get("deleted"), false));
        criteriaQuery.orderBy(cb.desc(root.<Long>get("id")));


        return cb.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}