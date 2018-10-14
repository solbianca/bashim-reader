package com.example.solbianca.bashim.Fragments;

import android.support.v7.widget.LinearLayoutManager;

import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Extractors.Parser.EmptyPaginationParser;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParser;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;

public class BestQuotesFragment extends QuotesFragment {

    @Override
    protected ExtractorInterface createExtractor() {
        return new QuoteExtractor(new QuotesParser(), new EmptyPaginationParser(), this.getQuotesRoute());
    }

    @Override
    protected void loadFirstPage() {
        this.loadPage(0);
    }

    @Override
    protected void initScrollListener(LinearLayoutManager layoutManager) {
    }
}
