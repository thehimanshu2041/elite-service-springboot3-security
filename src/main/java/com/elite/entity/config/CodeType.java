package com.elite.entity.config;

import com.elite.core.security.AuthUserStore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "elite_code_type")
public class CodeType {

    @Id
    @SequenceGenerator(name = "elite_code_type_seq", sequenceName = "elite_code_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elite_code_type_seq")
    @Column(name = "code_type_id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "code_type_code", nullable = false, unique = true)
    private String code;

    @Column(name = "code_type_name", nullable = false)
    private String name;

    @Column(name = "code_type_description", nullable = false)
    private String description;

    @Column(name = "code_type_created_by", nullable = false)
    private String createdBy;

    @Column(name = "code_type_updated_by", nullable = false)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "code_type_created_date", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "code_type_updated_date", nullable = false)
    private Date updatedDate;

    @OneToMany(mappedBy = "codeType", cascade = CascadeType.ALL)
    private List<Code> codes = new ArrayList<>();

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

    public List<Code> getCodes() {
        return codes;
    }

    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }
}
