package com.elite.entity.config;

import com.elite.core.security.AuthUserStore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "elite_ip_address")
public class IpAddress {

    @Id
    @SequenceGenerator(name = "elite_ip_address_seq", sequenceName = "elite_ip_address_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "elite_ip_address_seq")
    @Column(name = "ip_id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "ip_token")
    private String token;

    // Linked to User Table
    @Column(name = "ip_username")
    private String username;

    @Column(name = "ip_port")
    private String port;

    @Column(name = "ip_context_path")
    private String contextPath;

    @Column(name = "ip_request_path")
    private String requestPath;

    @Column(name = "ip_address")
    private String address;

    @Column(name = "ip_method")
    private String method;

    @Column(name = "ip_postal_code")
    private String postalCode;

    @Column(name = "ip_latitude")
    private String latitude;

    @Column(name = "ip_longitude")
    private String longitude;

    @Column(name = "ip_city")
    private String city;

    @Column(name = "ip_state")
    private String state;

    @Column(name = "ip_country")
    private String country;

    @Column(name = "ip_metro_code")
    private String metroCode;

    @Column(name = "ip_area_code")
    private String areaCode;

    @Column(name = "ip_num")
    private String num;

    @Column(name = "ip_url")
    private String url;

    @Column(name = "ip_referer")
    private String referer;

    @Column(name = "ip_created_by", nullable = false)
    private String createdBy;

    @Column(name = "ip_updated_by", nullable = false)
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "ip_created_date", nullable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "ip_updated_date", nullable = false)
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(String metroCode) {
        this.metroCode = metroCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
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
