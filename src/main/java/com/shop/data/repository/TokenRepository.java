package com.shop.data.repository;

import com.shop.data.entity.TokenEntity;
import com.shop.data.entity.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vazgen on 12/20/16.
 */
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByTokenTypeAndVal(TokenType tokenType, String token);
}
