package com.example.solbianca.bashim.Fragments;

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
import android.widget.Button;

import com.example.solbianca.bashim.Adapters.QuoteAdapter;
import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Listeners.EndlessRecyclerViewScrollListener;
import com.example.solbianca.bashim.R;
import com.example.solbianca.bashim.Tasks.QuotesPageTask;

public abstract class QuotesFragment extends Fragment {

    protected String quotesRoute;
    protected RecyclerView quoteRecyclerView;
    protected QuoteAdapter quoteAdapter;
    protected EndlessRecyclerViewScrollListener scrollListener;
    protected PageInterface quotesPage;

    public void setQuotesRoute(String quotesToParseType) {
        this.quotesRoute = quotesToParseType;
    }

    public void setQuotesPage(PageInterface quotesPage) {
        this.quotesPage = quotesPage;
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
        View view = getView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());

        this.quoteRecyclerView = view.findViewById(R.id.quote_recycler_view);
        this.quoteRecyclerView.setLayoutManager(layoutManager);
        this.quoteAdapter = new QuoteAdapter(this.getContext());
        this.quoteRecyclerView.setAdapter(this.quoteAdapter);
        this.quoteRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.initScrollListener(layoutManager);

        this.loadFirstPage();
        super.onActivityCreated(savedInstanceState);
    }

    public String getQuotesRoute() {
        return quotesRoute;
    }

    public QuoteAdapter getQuoteAdapter() {
        return quoteAdapter;
    }

    protected void loadPage(Integer pageNum) {
        this.quoteAdapter.showLoading(true);
        QuotesPageTask task = new QuotesPageTask(this, this.createExtractor());
        task.execute(pageNum);
    }

    protected abstract ExtractorInterface createExtractor();

    protected abstract void loadFirstPage();

    protected void loadNextPage() {
        Integer pageNum = this.quotesPage.getPager().getNext();
        this.loadPage(pageNum);
    }

    protected void initScrollListener(LinearLayoutManager layoutManager) {
        this.scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextPage();
            }
        };
        this.quoteRecyclerView.addOnScrollListener(this.scrollListener);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void voteUp(View view) {

    }
}
