package com.example.solbianca.bashim.Tasks;

import android.os.AsyncTask;

import com.example.solbianca.bashim.Adapters.QuotesAdapterInterface;
import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Components.Pages.QuotesPageAwareInterface;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;

public class QuotesPageTask extends AsyncTask<Integer, Void, PageInterface> {

    private QuotesPageAwareInterface fragment;
    private QuotesAdapterInterface adapter;
    private ExtractorInterface extractor;

    public QuotesPageTask(QuotesPageAwareInterface fragment, QuotesAdapterInterface adapter, ExtractorInterface extractor) {
        this.fragment = fragment;
        this.adapter = adapter;
        this.extractor = extractor;
    }

    @Override
    protected PageInterface doInBackground(Integer... pageNum) {
        return this.extractor.extract(pageNum[0]);
    }

    private QuotesAdapterInterface quotesAdapter() {
        return  this.adapter;
    }

    @Override
    protected void onPostExecute(PageInterface page) {
        this.fragment.setQuotesPage(page);
        this.quotesAdapter().showLoading(false);
        if (this.quotesAdapter().isEmptyQuotes()) {
            this.quotesAdapter().setQuotes(page.getQuotes());
        } else {
            this.quotesAdapter().addQuotes(page.getQuotes());
        }
    }
}
