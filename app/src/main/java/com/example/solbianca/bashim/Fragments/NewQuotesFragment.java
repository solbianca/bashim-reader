package com.example.solbianca.bashim.Fragments;

import com.example.solbianca.bashim.Components.Pager.QuotesPager;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Extractors.Parser.PaginationParser;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParser;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;


public class NewQuotesFragment extends QuotesFragment {

    protected ExtractorInterface createExtractor() {
        return new QuoteExtractor(new QuotesParser(), new PaginationParser(), this.getQuotesRoute());
    }

    protected void loadFirstPage() {
        this.loadPage(QuotesPager.NEW_QUOTES_START_PAGE);
    }
}
