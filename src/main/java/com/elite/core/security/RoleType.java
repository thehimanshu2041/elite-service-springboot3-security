package com.elite.core.security;

public enum RoleType {

    USER("USER"),
    ADMIN("ADMIN");

    private String code;

    RoleType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
