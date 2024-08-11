package com.elite.constants;

public enum EliteType {

    DEFAULT("DEFAULT"),
    ANONYMOUS("ANONYMOUS");

    private String code;

    EliteType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}