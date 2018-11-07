package com.example.solbianca.bashim.Tasks;

import android.os.AsyncTask;

import com.example.solbianca.bashim.Adapters.QuotesAdapter;
import com.example.solbianca.bashim.Adapters.QuotesAdapterInterface;
import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Components.Pages.QuotesPageAwareInterface;
import com.example.solbianca.bashim.Extractors.BestQuoteExtractor;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;

public class BestQuotesPageTask extends AsyncTask<Integer, Void, PageInterface> {

    private QuotesPageAwareInterface fragment;
    private QuotesAdapter adapter;
    private BestQuoteExtractor extractor;

    public BestQuotesPageTask(QuotesPageAwareInterface fragment, QuotesAdapter adapter, BestQuoteExtractor extractor) {
        this.fragment = fragment;
        this.adapter = adapter;
        this.extractor = extractor;
    }

    @Override
    protected PageInterface doInBackground(Integer... page) {
        this.quotesAdapter().clearQuotes();
        if (page.length == 0) {
            return  this.extractor.extract();
        } else if (page.length == 1) {
            return this.extractor.extract(page[0]);
        } else {
            return this.extractor.extract(page[0], page[1]);
        }
    }

    private QuotesAdapter quotesAdapter() {
        return  this.adapter;
    }

    @Override
    protected void onPostExecute(PageInterface page) {
        this.fragment.setQuotesPage(page);
        this.quotesAdapter().showLoading(false);
        this.quotesAdapter().addQuotes(page.getQuotes());
    }
}
