package com.tspoon.googlefinance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("source_code")
    private String sourceCode;

    @JsonProperty("code")
    private String code;

    @JsonProperty("data")
    private List<Quote> quotes;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getCode() {
        return code;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }
}
