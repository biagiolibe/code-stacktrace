package com.biagiolibe.dev.codestacktrace.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;


public class SourceCodeObject {
    private String id;
    private String code;
    private String language;//maybe use an enum

    @JsonCreator
    public SourceCodeObject(String id, String code, String language) {
        this.id = id;
        this.code = code;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
