package com.example.solbianca.bashim.Components.Pages;

public interface QuotesPageAwareInterface {

    public void setQuotesPage(PageInterface quotesPage);

    public void loadPage(Integer pageNum);

    public void loadFirstPage();
}
