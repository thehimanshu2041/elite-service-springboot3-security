package com.elite.entity.config;

import com.elite.core.security.AuthUserStore;
import com.elite.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "elite_code")
public class Code {

    @Id
    @SequenceGenerator(name = "elite_code_seq", sequenceName = "elite_code_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elite_code_seq")
    @Column(name = "code_id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "code_code", nullable = false, unique = true)
    private String code;

    @Column(name = "code_name", nullable = false)
    private String name;

    @Column(name = "code_description", nullable = false)
    private String description;

    @Column(name = "code_created_by", nullable = false)
    private String createdBy;

    @Column(name = "code_updated_by", nullable = false)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "code_created_date", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "code_updated_date", nullable = false)
    private Date updatedDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_code_type_id")
    private CodeType codeType;

    @OneToMany(mappedBy = "gender", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
