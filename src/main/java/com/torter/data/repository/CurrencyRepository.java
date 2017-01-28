package com.torter.data.repository;


import com.torter.data.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vazgen on 11/21/16.
 */
public interface CurrencyRepository extends JpaRepository<CurrencyEntity,Long> {
}
