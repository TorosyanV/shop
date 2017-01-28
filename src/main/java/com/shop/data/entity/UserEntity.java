package com.shop.data.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by vazgen on 12/20/16.
 */

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String passwordHash;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<RoleEntity> roles;

    @Column(name = "firstName", length = 50, nullable = true)
    private String firstName;

    @Column(name = "lastName", length = 50, nullable = true)
    private String lastName;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Date created;

    private Date updated;

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<EmailEntity> emails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId", nullable = true)
    private CityEntity city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Where(clause = "deleted = 0")
    private Set<ProductEntity> cars;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<TokenEntity> tokens;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<EmailEntity> getEmails() {
        return emails;
    }

    public void setEmails(Set<EmailEntity> emails) {
        this.emails = emails;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public Set<ProductEntity> getCars() {
        return cars;
    }

    public void setCars(Set<ProductEntity> cars) {
        this.cars = cars;
    }
}

