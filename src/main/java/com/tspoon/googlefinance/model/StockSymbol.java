package com.tspoon.googlefinance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockSymbol {

    private long id;

    private String name;

    @JsonProperty("source_code")
    private String sourceCode;

    private String code;

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
}
