package com.tspoon.googlefinance;

import com.tspoon.googlefinance.api.Api;
import com.tspoon.googlefinance.model.Quote;
import com.tspoon.googlefinance.model.Stock;
import com.tspoon.googlefinance.model.SymbolWrapper;
import com.tspoon.googlefinance.utils.AuthInterceptor;
import com.tspoon.googlefinance.utils.Utils;
import org.joda.time.DateTime;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
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

    public GoogleFinance(String quandlToken, RestAdapter.LogLevel logLevel) {
        mApi = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new JacksonConverter())
                .setRequestInterceptor(new AuthInterceptor(quandlToken))
                .setLogLevel(logLevel)
                .build()
                .create(Api.class);
    }

    public static void init() {
        init(null, RestAdapter.LogLevel.NONE);
    }

    public static void init(String quandlToken, RestAdapter.LogLevel logLevel) {
        if (sInstance == null) {
            sInstance = new GoogleFinance(quandlToken, logLevel);
        }
    }

    public static SymbolWrapper getSymbols(int page) {
        return sInstance.mApi.getSymbols(page);
    }

    public static List<Stock> getStocks(List<String> symbols) {
        return getStocks(symbols, DEFAULT_FROM, DEFAULT_TO);
    }

    public static List<Stock> getStocks(List<String> symbols, DateTime from, DateTime to) {
        List<Stock> stocks = symbols
                .stream()
                .parallel()
                .map(symbol -> getStock(symbol, from, to))
                .filter(stock -> stock != null)
                .collect(Collectors.toList());

        processStockList(stocks);

        return stocks;
    }

    public static Stock getStock(String symbol) {
        return getStock(symbol, DEFAULT_FROM, DEFAULT_TO);
    }

    public static Stock getStock(String symbol, DateTime from, DateTime to) {
        try {
            return sInstance.mApi.getStock(DATA_SOURCE, symbol, Utils.toDateString(from), Utils.toDateString(to));
        } catch (RetrofitError e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Stock> getSAndP500Stocks() {
        return getSAndP500Stocks(DEFAULT_FROM, DEFAULT_TO);
    }

    public static List<Stock> getSAndP500Stocks(DateTime from, DateTime to) {
        InputStream is = GoogleFinance.class.getResourceAsStream("/s&p500.txt");
        String stockString = Utils.getStringFromInputStream(is);

        List<String> symbols = Arrays.stream(stockString.split(",")).collect(Collectors.toList());

        return getStocks(symbols, from, to);
    }

    public static void processStockList(List<Stock> stocks) {
        for (Stock stock : stocks) {
            List<Quote> quotes = stock.getQuotes();

            int numQuotes = quotes.size();
            for (int i = 0; i < numQuotes - 1; i++) {
                Quote quote = quotes.get(i);
                Quote previousDay = quotes.get(i + 1);
                quote.setPreviousClose(previousDay.getClose());
            }
        }
    }

    public static enum Period {
        EXPANSION_2009("2009-03-06", Utils.toDateString(new DateTime())),
        CONTRACTION_2007("2007-07-13", "2009-03-06"),
        EXPANSION_2002("2002-10-04", "2007-07-13"),
        PAST_10_YEARS(Utils.toDateString(new DateTime().minusYears(10)), Utils.toDateString(new DateTime()));

        public final DateTime dateFrom;
        public final DateTime dateTo;

        Period(String dateFrom, String dateTo) {
            this.dateFrom = Utils.dateFromString(dateFrom);
            this.dateTo = Utils.dateFromString(dateTo);
        }
    }
}
