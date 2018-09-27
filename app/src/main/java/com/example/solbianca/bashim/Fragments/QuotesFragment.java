package com.example.solbianca.bashim.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.solbianca.bashim.Adapters.QuoteAdapter;
import com.example.solbianca.bashim.Components.QuotesPager;
import com.example.solbianca.bashim.Entities.Quote;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;
import com.example.solbianca.bashim.Listeners.EndlessRecyclerViewScrollListener;
import com.example.solbianca.bashim.R;

import java.util.ArrayList;

public class QuotesFragment extends Fragment {

    private String quotesRoute;
    private QuotesPager quotesPager;

    private RecyclerView quoteRecyclerView;
    private QuoteAdapter quoteAdapter;

    private EndlessRecyclerViewScrollListener scrollListener;

    public void setQuotesRoute(String quotesToParseType) {
        this.quotesRoute = quotesToParseType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.quotesRoute.isEmpty()) {
            throw new RuntimeException("Quotes fragment type cant be empty");
        }
        return inflater.inflate(R.layout.quotes_fragment, viewGroup, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i("dump", "lol: " + "lol");
        View view = getView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        this.quoteRecyclerView = view.findViewById(R.id.quote_recycler_view);
        this.quoteRecyclerView.setLayoutManager(layoutManager);
        this.quoteAdapter = new QuoteAdapter(this.getContext());
        this.quoteRecyclerView.setAdapter(this.quoteAdapter);

        this.quoteRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextPage();
            }
        };
        this.quoteRecyclerView.addOnScrollListener(this.scrollListener);

        loadFirstPage();

        QuotesPaginatorTask paginator = new QuotesPaginatorTask(this.quotesRoute);
        paginator.execute();

        super.onActivityCreated(savedInstanceState);
    }

    private void loadFirstPage() {
        this.quoteAdapter.showLoading(true);
        QuotesTask task = new QuotesTask(this.quotesRoute);
        task.execute(QuotesPager.NEW_QUOTES_START_PAGE);
    }

    private void loadNextPage() {
        Integer page = this.quotesPager.getNextForNew();
        Log.i("dump", "next page: " + page);
        this.quotesPager.setCurrentNew(page);
        this.quoteAdapter.showLoading(true);
        QuotesTask task = new QuotesTask(this.quotesRoute);
        task.execute(page);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private class QuotesPaginatorTask extends AsyncTask<Void, Void, QuotesPager> {

        String route;

        public QuotesPaginatorTask(String route) {
            Log.i("dump", "pager task: create");
            this.route = route;
        }

        @Override
        protected QuotesPager doInBackground(Void... params) {
            Log.i("dump", "pager task: run");
            QuoteExtractor extractor = new QuoteExtractor(this.route);
            QuotesPager quotesPager = extractor.pagination();
            return quotesPager;
        }

        @Override
        protected void onPostExecute(QuotesPager pager) {
            Log.i("dump", "pager task: done");
            quotesPager = pager;
        }
    }

    private class QuotesTask extends AsyncTask<Integer, Void, ArrayList<Quote>> {

        private String route;

        public QuotesTask(String route) {
            Log.i("dump", "quotes task: create");
            this.route = route;
        }

        @Override
        protected ArrayList<Quote> doInBackground(Integer... pageNum) {
            Log.i("dump", "quotes task: run");
            Log.i("dump", "quotes task page: " + pageNum[0]);
            QuoteExtractor extractor = new QuoteExtractor(this.route);
            ArrayList<Quote> quotes = extractor.extract(pageNum[0]);
            return quotes;
        }

        @Override
        protected void onPostExecute(ArrayList<Quote> quotes) {
            Log.i("dump", "quotes task: done");
            quoteAdapter.showLoading(false);
            if (quoteAdapter.isEmptQuotes()) {
                Log.i("dump", "quotes task: setQuotes quotes");
                quoteAdapter.setQuotes(quotes);
            } else {
                Log.i("dump", "quotes task: addQuote quotes");
                quoteAdapter.addQuotes(quotes);
            }
        }
    }
}
