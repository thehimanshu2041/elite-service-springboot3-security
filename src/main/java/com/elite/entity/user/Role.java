package com.elite.entity.user;

import com.elite.core.security.AuthUserStore;
import com.elite.core.security.RoleType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "elite_role")
public class Role {

    @Id
    @SequenceGenerator(name = "elite_role_seq", sequenceName = "elite_role_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elite_role_seq")
    @Column(name = "role_id", insertable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleType name;

    @Column(name = "role_description", nullable = false)
    private String description;

    @Column(name = "role_created_by", nullable = false)
    private String createdBy;

    @Column(name = "role_updated_by", nullable = false)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "role_created_date", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "role_updated_date", nullable = false)
    private Date updatedDate;

    // Link to User Table
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> users;

    @PrePersist
    private void prePersistFunction() {
        AuthUserStore authUserStore = new AuthUserStore();
        this.createdBy = authUserStore.getLoggedInUser();
        this.updatedBy = authUserStore.getLoggedInUser();
    }

    @PreUpdate
    public void preUpdateFunction() {
        AuthUserStore authUserStore = new AuthUserStore();
        this.updatedBy = authUserStore.getLoggedInUser();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
