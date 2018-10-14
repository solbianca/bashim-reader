package com.example.solbianca.bashim.Fragments;

import com.example.solbianca.bashim.Components.Pager.BestByRatingQuotesPager;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Extractors.Parser.BestByRatingPaginationParser;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParser;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;

public class BestByRatingQuotesFragment extends QuotesFragment {

    protected void loadFirstPage() {
        this.loadPage(BestByRatingQuotesPager.BEST_QUOTES_BY_RATING_START_PAGE);
    }

    protected ExtractorInterface createExtractor() {
        return new QuoteExtractor(new QuotesParser(), new BestByRatingPaginationParser(), this.getQuotesRoute());
    }
}
