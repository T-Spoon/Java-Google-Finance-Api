package com.tspoon.googlefinance.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

import java.util.Collections;
import java.util.Comparator;
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

    public Quote getQuoteOn(DateTime date) {
        int index = getQuoteIndexOn(date);

        if (index >= 0) {
            return quotes.get(index);
        }

        return null;
    }

    public int getQuoteIndexOn(DateTime date) {
        Quote fake = new Quote(date);

        return Collections.binarySearch(quotes, fake, new Comparator<Quote>() {
            @Override
            public int compare(Quote o1, Quote o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }
}
