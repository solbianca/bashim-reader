package com.example.solbianca.bashim.Components.Pages;

import com.example.solbianca.bashim.Components.Pager.PagerInterface;
import com.example.solbianca.bashim.Entities.Quote;

import java.util.ArrayList;

public interface PageInterface {

    public ArrayList<Quote> getQuotes();

    public PagerInterface getPager();
}
