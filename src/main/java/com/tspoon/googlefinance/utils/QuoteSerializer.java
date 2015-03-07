package com.tspoon.googlefinance.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tspoon.googlefinance.model.Quote;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;

public class QuoteSerializer extends JsonSerializer<Quote> {

    private static final org.joda.time.format.DateTimeFormatter FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public void serialize(Quote value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartArray();
        gen.writeObject(value.getDate().toString(FORMAT));
        gen.writeNumber(value.getOpen());
        gen.writeNumber(value.getHigh());
        gen.writeNumber(value.getLow());
        gen.writeNumber(value.getClose());
        gen.writeNumber(value.getVolume());
        gen.writeNumber(value.getPrevClose());
        gen.writeEndArray();
    }
}
