package com.example.solbianca.bashim.Extractors.Parser;

import com.example.solbianca.bashim.Entities.Quote;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class QuotesParser implements QuotesParserInterface {

    private ArrayList<Quote> quotes;

    public QuotesParser() {
    }

    public QuotesParser parse(String page) {
        Document document = Jsoup.parse(page);
        Elements parsedQuotes = document.select("div.quote");

        if (parsedQuotes.isEmpty()) {
            return null;
        }

        ArrayList<Quote> quotes = new ArrayList<Quote>();
        for (Element parsedQuote : parsedQuotes) {
            String parsedId = parsedQuote.select("a.id").html();
            if (parsedId.isEmpty()) {
                continue;
            }

            Quote quote = new Quote();
            quote.setId(Integer.parseInt(parsedId.replaceAll("[\\D]", "")));
            quote.setRating(parsedQuote.select("span.rating").html());
            quote.setDate(parsedQuote.select("span.date").html());
            quote.setText(parsedQuote.select("div.text").html());
            quotes.add(quote);
        }
        this.quotes = quotes;
        return  this;
    }

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }
}
