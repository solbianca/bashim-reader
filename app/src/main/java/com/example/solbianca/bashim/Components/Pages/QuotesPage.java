package com.example.solbianca.bashim.Components.Pages;

import com.example.solbianca.bashim.Components.Pager.PagerInterface;
import com.example.solbianca.bashim.Entities.Quote;

import java.util.ArrayList;

public class QuotesPage implements PageInterface{

    private ArrayList<Quote> quotes;
    private PagerInterface pager;

    public QuotesPage(ArrayList<Quote> quotes, PagerInterface pager) {
        this.quotes = quotes;
        this.pager = pager;
    }

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }

    public PagerInterface getPager() {
        return pager;
    }
}
