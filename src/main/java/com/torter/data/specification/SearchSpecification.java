package com.torter.data.specification;


import com.torter.data.entity.ProductEntity;
import com.torter.data.specification.filter.SearchFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


        //if searching with model, no need make
        if (filter.getModelId() != null) {
            predicates.add(
                    cb.or(
                        cb.equal(root.<Long>get("model").get("id"), filter.getModelId()),
                        cb.equal(root.<Long>get("model").get("parentModel").get("id"), filter.getModelId())
                    ));
        } else if (filter.getMakeId() != null) {
                predicates.add(cb.equal(root.<Long>get("model").get("make").get("id"), filter.getMakeId()));
        }

        if (filter.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.<Double>get("detail").get("price"), filter.getMaxPrice()));
        }

        if (filter.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.<Double>get("detail").get("price"), filter.getMinPrice()));
        }


        if (filter.getBodyTypeIds() != null) {

            predicates.add(root.<Long>get("body").get("bodyType").get("id").in(filter.getBodyTypeIds()));
        }

        if (filter.getMinYear() != null){
            predicates.add(cb.greaterThanOrEqualTo(root.<Integer>get("detail").get("year"), filter.getMinYear()));
        }

        if (filter.getMaxYear() != null){
            predicates.add(cb.lessThanOrEqualTo(root.<Integer>get("detail").get("year"), filter.getMaxYear()));
        }

        if (filter.getMetallic() != null){
            predicates.add(cb.equal(root.<Boolean>get("body").get("isMetallic"), filter.getMetallic()));
        }

        if (filter.getCapacityId() != null) {
            predicates.add(cb.equal(root.<Long>get("engine").get("capacity").get("id"), filter.getCapacityId()));
        }

        if (filter.getDamaged() != null){
            predicates.add(cb.equal(root.<Boolean>get("detail").get("damaged"), filter.getDamaged()));
        }

        if (filter.getImmediately() != null){
            predicates.add(cb.equal(root.<Boolean>get("detail").get("immediately"), filter.getImmediately()));
        }

        if (filter.getFuelTypeId() != null){
            predicates.add(cb.equal(root.<Long>get("engine").get("fuelType").get("id"), filter.getFuelTypeId()));
        }



        if (filter.getGearTypeId() != null) {
            predicates.add(cb.equal(root.<String>get("gear").get("gearType").get("id"), filter.getGearTypeId()));
        }

        if (filter.getWithImage() != null && filter.getWithImage()) {
            predicates.add(cb.isNotNull(root.get("mainImage")));
        }

        if (filter.getCustomCleared() != null) {
            predicates.add(cb.equal(root.<Boolean>get("detail").get("customCleared"), filter.getCustomCleared()));
        }

        if (filter.getColorIds() != null){
            predicates.add(root.<List<Long>>get("body").get("color").get("id").in(filter.getColorIds()));

        }
        predicates.add(cb.equal(root.<Boolean>get("deleted"), false));
        criteriaQuery.orderBy(cb.desc(root.<Long>get("id")));


        return cb.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}