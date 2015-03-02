package com.tspoon.googlefinance;

import com.tspoon.googlefinance.api.Api;
import com.tspoon.googlefinance.model.Stock;
import com.tspoon.googlefinance.model.SymbolWrapper;
import com.tspoon.googlefinance.utils.AuthInterceptor;
import com.tspoon.googlefinance.utils.Utils;
import org.joda.time.DateTime;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleFinance {

    private static final String ENDPOINT = "http://www.quandl.com/api/v1";
    private static final String DATA_SOURCE = "GOOG";
    private static final DateTime DEFAULT_TO = new DateTime();
    private static final DateTime DEFAULT_FROM = new DateTime(DEFAULT_TO.minusYears(1));

    private static GoogleFinance sInstance;

    private Api mApi;

    public GoogleFinance(String quandlToken) {
        mApi = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new JacksonConverter())
                .setRequestInterceptor(new AuthInterceptor(quandlToken))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build()
                .create(Api.class);
    }

    public static void init() {
        init(null);
    }

    public static void init(String quandlToken) {
        if (sInstance == null) {
            sInstance = new GoogleFinance(quandlToken);
        }
    }

    public static SymbolWrapper getSymbols(int page) {
        return sInstance.mApi.getSymbols(page);
    }

    public static List<Stock> getStocks(List<String> symbols) {
        return getStocks(symbols, DEFAULT_FROM, DEFAULT_TO);
    }

    public static List<Stock> getStocks(List<String> symbols, DateTime from, DateTime to) {
        return symbols.stream().parallel()
                .map(symbol -> getStock(symbol, from, to))
                .collect(Collectors.toList());
    }

    public static Stock getStock(String symbol) {
        return getStock(symbol, DEFAULT_FROM, DEFAULT_TO);
    }

    public static Stock getStock(String symbol, DateTime from, DateTime to) {
        return sInstance.mApi.getStock(DATA_SOURCE, symbol, Utils.toDateString(from), Utils.toDateString(to));
    }

    public static List<Stock> getSAndP500Stocks() {
        return getSAndP500Stocks(DEFAULT_FROM, DEFAULT_TO);
    }

    public static List<Stock> getSAndP500Stocks(DateTime from, DateTime to) {
        InputStream is = GoogleFinance.class.getResourceAsStream("/s&p500.txt");
        String stockString = Utils.getStringFromInputStream(is);

        List<String> symbols = Arrays.stream(stockString.split(",")).limit(10).collect(Collectors.toList());

        return getStocks(symbols, from, to);
    }
}
