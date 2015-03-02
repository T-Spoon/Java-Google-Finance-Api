// Use this script to extract a list of S&P 500 ticker symbols from Wikipedia: https://en.wikipedia.org/wiki/List_of_S%26P_500_companies

/*
var elements = [];
$(".wikitable.sortable.jquery-tablesorter tr td:first-child a").each(function(index, element) {
    elements.push({ 's': $(element).text() });
});
JSON.stringify(elements);
*/

var elements = [];
$(".wikitable.sortable.jquery-tablesorter tr td:first-child a").each(function(index, element) {
    var href = $(element).attr('href');
    var exchange = "";
    if(href.indexOf('nyse') >= 0) {
        exchange = 'NYSE';
    } else if (href.indexOf('nasdaq') >= 0) {
        exchange = 'NASDAQ';
    }

    var symbol = $(element).text();
    elements.push(exchange + '_' + symbol);
});
var output = "";
for(var i = 0; i < elements.length; i++) { output += elements[i] + ","; };
console.log(output)