package com.shop.data.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vazgen on 06-Jan-17.
 */
@Entity
@Table(name = "token")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "val", nullable = false)
    private String val;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tokenType", nullable = false)
    private TokenType tokenType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Date created;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }


    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
}

