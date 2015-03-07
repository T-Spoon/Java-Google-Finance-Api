package com.tspoon.googlefinance.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tspoon.googlefinance.model.Quote;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class QuoteDeserializer extends JsonDeserializer<Quote> {

    private static final DateTimeFormatter FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public Quote deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = ((ObjectMapper) oc).readTree(p);

        Quote quote = new Quote(
                FORMAT.parseDateTime(node.get(0).asText()),
                node.get(1).asDouble(),
                node.get(2).asDouble(),
                node.get(3).asDouble(),
                node.get(4).asDouble(),
                node.get(5).asLong()
        );

        // If we're deserializing from the API this field won't exist
        if (node.size() >= 7) {
            quote.setPreviousClose(node.get(6).asDouble());
        }

        return quote;
    }
}
