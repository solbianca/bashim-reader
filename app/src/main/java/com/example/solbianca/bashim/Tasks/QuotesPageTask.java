package com.example.solbianca.bashim.Tasks;

import android.os.AsyncTask;

import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Fragments.QuotesFragment;

import java.util.ArrayList;

public class QuotesPageTask extends AsyncTask<Integer, Void, PageInterface> {

    private QuotesFragment fragment;
    private ExtractorInterface extractor;

    public QuotesPageTask(QuotesFragment fragment, ExtractorInterface extractor) {
        this.fragment = fragment;
        this.extractor = extractor;
    }

    @Override
    protected PageInterface doInBackground(Integer... pageNum) {
        return this.extractor.extract(pageNum[0]);
    }

    @Override
    protected void onPostExecute(PageInterface page) {
        this.fragment.setQuotesPage(page);
        this.fragment.getQuoteAdapter().showLoading(false);
        if (this.fragment.getQuoteAdapter().isEmptQuotes()) {
            this.fragment.getQuoteAdapter().setQuotes(page.getQuotes());
        } else {
            this.fragment.getQuoteAdapter().addQuotes(page.getQuotes());
        }
    }
}
