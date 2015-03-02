package com.tspoon.googlefinance.api;

import com.tspoon.googlefinance.model.Stock;
import com.tspoon.googlefinance.model.SymbolWrapper;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Api {

    @GET("/datasets.json?query=*&source_code=GOOG&per_page=300")
    public SymbolWrapper getSymbols(@Query("page") int page);

    @GET("/datasets/{source}/{symbol}.json")
    public Stock getStock(@Path("source") String source, @Path("symbol") String symbol, @Query("trim_start") String from, @Query("trim_end") String to);
}
