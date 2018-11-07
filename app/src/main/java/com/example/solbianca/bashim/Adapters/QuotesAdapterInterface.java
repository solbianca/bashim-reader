package com.example.solbianca.bashim.Adapters;

import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Entities.Quote;

import java.util.ArrayList;

public interface QuotesAdapterInterface {

    public void showLoading(boolean status);

    public boolean isEmptyQuotes();

    public void setQuotes(ArrayList<Quote> quotes);

    public void addQuotes(ArrayList<Quote> quotes);
}
