package com.example.solbianca.bashim.Extractors.Parser;

import com.example.solbianca.bashim.Entities.Quote;

import java.util.ArrayList;

public interface QuotesParserInterface extends ParserInterface {

    public QuotesParserInterface parse(String page);

    public ArrayList<Quote> getQuotes();
}
