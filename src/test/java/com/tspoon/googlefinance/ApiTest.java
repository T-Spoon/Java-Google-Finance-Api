package com.tspoon.googlefinance;

import com.tspoon.googlefinance.model.Stock;
import com.tspoon.googlefinance.model.StockSymbol;
import com.tspoon.googlefinance.model.SymbolWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApiTest {

    private SymbolWrapper symbols;

    @Before
    public void setUp() {
        GoogleFinance.init();
    }

    @Test
    public void checkSymbols() {
        for (StockSymbol s : symbols.getSymbols()) {
            Assert.assertNotNull(s.getId());
            Assert.assertNotNull(s.getName());
            Assert.assertNotNull(s.getCode());
            Assert.assertNotNull(s.getSourceCode());
        }
    }

    @Test
    public void testStock() {
        Stock stock = GoogleFinance.getStock(symbols.getSymbols().get(0).getCode());
    }
}
