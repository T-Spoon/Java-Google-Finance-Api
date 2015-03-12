package com.tspoon.googlefinance.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.tspoon.googlefinance.utils.QuoteDeserializer;
import com.tspoon.googlefinance.utils.QuoteSerializer;
import com.tspoon.googlefinance.utils.Utils;
import org.joda.time.DateTime;

@JsonDeserialize(using = QuoteDeserializer.class)
@JsonSerialize(using = QuoteSerializer.class)
public class Quote {

    private DateTime date;

    private double open;

    private double high;

    private double low;

    private double close;

    private long volume;

    private double prevClose;

    public Quote(DateTime date) {
        this.date = date;
    }

    public Quote(DateTime date, double open, double high, double low, double close, long volume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
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

    public double getPrevClose() {
        return prevClose;
    }

    public double getChangeInPercent() {
        return Utils.getPercent(close - open, close);
    }

    public double getChangeFromPrevious(Quote other) {
        return Utils.getPercent(close - other.getClose(), close);
    }

    /*
     * In Percent
     */
    public double getInterDayChange() {
        if (prevClose == 0) {
            return 0;
        }
        return Utils.getPercent(close - prevClose, close);
    }

    public void setPreviousClose(double prevClose) {
        this.prevClose = prevClose;
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
