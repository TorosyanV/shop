package com.shop.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by vazgen on 8/13/16.
 */
@Entity
@Table(name = "equipment")
public class CompositionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
