package com.elite.entity.user;


import com.elite.core.security.AuthUserStore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "elite_user_setting")
public class UserSetting {

    @Id
    @SequenceGenerator(name = "elite_user_setting_seq", sequenceName = "elite_user_setting_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elite_user_setting_seq")
    @Column(name = "user_setting_id", insertable = false, updatable = false)
    private Long id;

    // Linked to User table
    @Column(name = "user_setting_user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "user_setting_uid", nullable = false)
    private String uid;

    @Column(name = "user_setting_secret", nullable = false)
    private String secret;

    @Column(name = "user_setting_token", nullable = false)
    private String token;

    @Column(name = "user_setting_notification", nullable = false)
    private Boolean notification;

    @Column(name = "user_setting_email", nullable = false)
    private Boolean email;

    @Column(name = "user_setting_night_mode", nullable = false)
    private Boolean nightMode;

    @Column(name = "user_setting_created_by", nullable = false)
    private String createdBy;

    @Column(name = "user_setting_updated_by", nullable = false)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "user_setting_created_date", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "user_setting_updated_date", nullable = false)
    private Date updatedDate;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public Boolean getNightMode() {
        return nightMode;
    }

    public void setNightMode(Boolean nightMode) {
        this.nightMode = nightMode;
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
}
