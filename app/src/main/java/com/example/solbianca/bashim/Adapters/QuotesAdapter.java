package com.example.solbianca.bashim.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Components.Pages.QuotesPage;
import com.example.solbianca.bashim.Entities.Quote;
import com.example.solbianca.bashim.R;
import com.example.solbianca.bashim.ViewHolders.LoaderViewHolder;
import com.example.solbianca.bashim.ViewHolders.QuoteViewHolder;

import java.util.ArrayList;
import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements QuotesAdapterInterface {

    private static final Integer VIEW_TYPE_ITEM = 0;
    private static final Integer VIEW_TYPE_LOADER = 1;

    protected boolean showLoader;

    private ArrayList<Quote> quotes = new ArrayList<Quote>();
    private Context context;

    private PageInterface quotesPage;

    public QuotesAdapter(Context context) {
        this.context = context;
    }

    public void setQuotesPage(PageInterface quotesPage) {
        this.quotesPage = quotesPage;
    }

    public PageInterface getQuotesPage() {
        return this.quotesPage;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // Loader ViewHolder
        if (viewHolder instanceof LoaderViewHolder) {
            LoaderViewHolder loaderViewHolder = (LoaderViewHolder) viewHolder;
            if (showLoader) {
                loaderViewHolder.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                loaderViewHolder.mProgressBar.setVisibility(View.GONE);
            }
            return;
        }

        if (viewHolder instanceof QuoteViewHolder) {
            QuoteViewHolder quoteViewHolder1 = (QuoteViewHolder) viewHolder;
            quoteViewHolder1.bind(quotes.get(position));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if (viewType == VIEW_TYPE_LOADER) {
            View view = inflater.inflate(R.layout.loader_item_view, viewGroup, false);
            return new LoaderViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.quote_item_view, viewGroup, false);
            return new QuoteViewHolder(view, this.context);
        }

        throw new IllegalArgumentException("Invalid ViewType: " + viewType);
    }

    @Override
    public int getItemCount() {
        // If no items are present, there's no need for loader
        if (quotes == null || quotes.size() == 0) {
            return 0;
        }

        // +1 for loader
        return quotes.size() + 1;
    }

    @Override
    public long getItemId(int position) {
        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {

            // id of loader is considered as -1 here
            return -1;
        }
        return quotes.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {
            return VIEW_TYPE_LOADER;
        }

        return VIEW_TYPE_ITEM;
    }

    public void showLoading(boolean status) {
        showLoader = status;
        notifyDataSetChanged();
    }

    public void setQuotes(ArrayList<Quote> quotes) {
        clearQuotes();
        addQuotes(quotes);
    }

    public void addQuote(Quote mc) {
        this.quotes.add(mc);
        notifyItemInserted(this.quotes.size() - 1);
    }

    public void addQuotes(ArrayList<Quote> quotes) {
        this.quotes.addAll(quotes);
        notifyDataSetChanged();
    }

    public void clearQuotes() {
        this.quotes.clear();
        notifyDataSetChanged();
    }

    public boolean isEmptyQuotes() {
        return getItemCount() == 0;
    }

    public Quote getQuote(int position) {
        return this.quotes.get(position);
    }
}
