package com.tspoon.googlefinance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolWrapper {

    @JsonProperty("total_count")
    private int count;

    @JsonProperty("current_page")
    private int page;

    @JsonProperty("docs")
    private List<StockSymbol> symbols;

    public int getCount() {
        return count;
    }

    public int getPage() {
        return page;
    }

    public List<StockSymbol> getSymbols() {
        return symbols;
    }
}
