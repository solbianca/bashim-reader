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

import com.example.solbianca.bashim.Adapters.QuotesAdapterInterface;
import com.example.solbianca.bashim.Adapters.QuotesWithWantMoreAdapter;
import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Components.Pages.QuotesPageAwareInterface;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParser;
import com.example.solbianca.bashim.Extractors.Parser.WantMorePaginationParser;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;
import com.example.solbianca.bashim.R;
import com.example.solbianca.bashim.Tasks.QuotesPageTask;

public class RandomQuotesFragment extends QuotesFragment implements QuotesPageAwareInterface {


    private String quotesRoute;
    private RecyclerView quoteRecyclerView;
    private QuotesWithWantMoreAdapter quotesAdapter;
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

        this.quotesAdapter = new QuotesWithWantMoreAdapter(this.getContext(), this.listener);

        this.quoteRecyclerView = view.findViewById(R.id.quote_recycler_view);
        this.quoteRecyclerView.setLayoutManager(layoutManager);
        this.quoteRecyclerView.setAdapter(this.quotesAdapter);
        this.quoteRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.hideCalendarPager(view, this.quoteRecyclerView);

        this.loadFirstPage();
        super.onActivityCreated(savedInstanceState);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i("dump", "click: go!");
            try {
                loadPage(quotesPage.getPager().getNext());
            } catch (NullPointerException e) {
                loadPage(0);
            }
        }
    };

    public String getQuotesRoute() {
        return quotesRoute;
    }

    public RecyclerView.Adapter getQuotesAdapter() {
        return quotesAdapter;
    }

    public void loadPage(Integer pageNum) {
        Log.i("dump", "page: " + pageNum);
        this.quotesAdapter.showLoading(true);
        QuotesPageAwareInterface fragment = (QuotesPageAwareInterface)this;
        QuotesAdapterInterface adapter = (QuotesAdapterInterface) this.quotesAdapter;
        QuotesPageTask task = new QuotesPageTask(fragment, adapter, this.createExtractor());
        task.execute(pageNum);
    }

    public void loadFirstPage() {
        this.loadPage(0);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private ExtractorInterface createExtractor() {
        return new QuoteExtractor(new QuotesParser(), new WantMorePaginationParser(), this.getQuotesRoute());
    }
}
