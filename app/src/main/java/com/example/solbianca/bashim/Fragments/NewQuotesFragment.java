package com.example.solbianca.bashim.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.solbianca.bashim.Adapters.QuotesAdapter;
import com.example.solbianca.bashim.Adapters.QuotesAdapterInterface;
import com.example.solbianca.bashim.Adapters.QuotesWithWantMoreAdapter;
import com.example.solbianca.bashim.Components.Pager.QuotesPager;
import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Components.Pages.QuotesPageAwareInterface;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Extractors.Parser.PaginationParser;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParser;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;
import com.example.solbianca.bashim.Listeners.EndlessRecyclerViewScrollListener;
import com.example.solbianca.bashim.R;
import com.example.solbianca.bashim.Tasks.QuotesPageTask;


public class NewQuotesFragment extends QuotesFragment implements QuotesPageAwareInterface {

    private String quotesRoute;
    private RecyclerView quoteRecyclerView;
    private QuotesAdapter quotesAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private PageInterface quotesPage;

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

        this.quotesAdapter = new QuotesAdapter(this.getContext());

        this.quoteRecyclerView = view.findViewById(R.id.quote_recycler_view);
        this.quoteRecyclerView.setLayoutManager(layoutManager);
        this.quoteRecyclerView.setAdapter(this.quotesAdapter);
        this.quoteRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.initScrollListener(layoutManager);

        this.hideCalendarPager(view, this.quoteRecyclerView);

        this.loadFirstPage();
        super.onActivityCreated(savedInstanceState);
    }

    public String getQuotesRoute() {
        return quotesRoute;
    }

    public RecyclerView.Adapter getQuotesAdapter() {
        return quotesAdapter;
    }

    public void loadPage(Integer pageNum) {
        this.quotesAdapter.showLoading( true);
        QuotesPageAwareInterface fragment = (QuotesPageAwareInterface)this;
        QuotesAdapterInterface adapter = (QuotesAdapterInterface) this.quotesAdapter;
        QuotesPageTask task = new QuotesPageTask(fragment, adapter, this.createExtractor());
        task.execute(pageNum);
    }

    public void loadNextPage() {
        Integer pageNum = this.quotesPage.getPager().getNext();
        this.loadPage(pageNum);
    }

    private void initScrollListener(LinearLayoutManager layoutManager) {
        this.scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextPage();
            }
        };
        this.quoteRecyclerView.addOnScrollListener(this.scrollListener);
    }

    public void onStart() {
        super.onStart();
    }

    private ExtractorInterface createExtractor() {
        return new QuoteExtractor(new QuotesParser(), new PaginationParser(), this.getQuotesRoute());
    }

    public void loadFirstPage() {
        this.loadPage(QuotesPager.NEW_QUOTES_START_PAGE);
    }
}
