package com.example.solbianca.bashim.Fragments;

import android.support.v7.widget.LinearLayoutManager;

import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParser;
import com.example.solbianca.bashim.Extractors.Parser.WantMorePaginationParser;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;

import retrofit2.Retrofit;

public class RandomQuotesFragment extends QuotesFragment {

    @Override
    protected ExtractorInterface createExtractor() {
        return new QuoteExtractor(new QuotesParser(), new WantMorePaginationParser(), this.getQuotesRoute());
    }

    @Override
    protected void loadFirstPage() {
        this.loadPage(0);
    }

    @Override
    protected void initScrollListener(LinearLayoutManager layoutManager) {
    }
}
