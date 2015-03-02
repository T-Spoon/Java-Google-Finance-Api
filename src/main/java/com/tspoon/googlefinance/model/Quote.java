package com.tspoon.googlefinance.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tspoon.googlefinance.utils.QuoteDeserializer;
import com.tspoon.googlefinance.utils.Utils;
import org.joda.time.DateTime;

@JsonDeserialize(using = QuoteDeserializer.class)
public class Quote {

    private DateTime date;

    private double open;

    private double high;

    private double low;

    private double close;

    private long volume;

    public Quote(DateTime date, double open, double high, double low, double close, long volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public DateTime getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public long getVolume() {
        return volume;
    }

    public double getChangeInPercent() {
        return Utils.getPercent(close - open, close);
    }

    public double getChangeFromPrevious(Quote other) {
        return Utils.getPercent(close - other.getClose(), close);
    }

    @Override
    public String toString() {
        return Utils.toDateString(date) + ": " + open + " --> " + close + " ( " + Utils.toPercent(getChangeInPercent()) + " )";
    }

    /*
    @JsonProperty("6")
    private float exDividend;

    @JsonProperty("7")
    private float splitRatio;

    @JsonProperty("8")
    private float adjOpen;

    @JsonProperty("9")
    private float adjHigh;

    @JsonProperty("10")
    private float adjLow;

    @JsonProperty("11")
    private float adjClose;

    @JsonProperty("12")
    private float adjVolume;
    */
}
