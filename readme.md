# Google Finance Java API
An opinionated Java API for Google Finance data - using [Quandl], [Retrofit], [Joda Time] and [Jackson].  Currently only Java 8 is supported (but it should be easily possible to backport it to Java 7).  I just wanted to play with lamdas and streams.

# Usage

#### Initialize
```java
GoogleFinance.init();
// Optionally you can initialize with your Quandl Api key to get a higher request limit
GoogleFinance.init("QuandlApiKey");
```
#### Get Some Sweet Data
```java
// All stocks must be prefixed with 'STOCKEXCHANGE_'.  This is a quirk of the Google Finance Quandl feed
Stock stock = GoogleFinance.getStock("NASDAQ_TSLA");

// Optionally supply from and to dates for quote history
Stock stock = GoogleFinance.getStock("NASDAQ_TSLA", new DateTime().withYear(2011), new DateTime().withYear(2012));

// Or get all the S&P 500 stocks
List<Stock> stocks = GoogleFinance.getSAndP500Stocks();
```
That's all for now folks!

[Quandl]:https://www.quandl.com/
[Retrofit]:https://github.com/square/retrofit
[Jackson]:http://jackson.codehaus.org/
[Joda Time]:http://www.joda.org/joda-time/
