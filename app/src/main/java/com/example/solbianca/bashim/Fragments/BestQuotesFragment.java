package com.example.solbianca.bashim.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.solbianca.bashim.Adapters.QuotesAdapter;
import com.example.solbianca.bashim.Adapters.QuotesAdapterInterface;
import com.example.solbianca.bashim.Components.Pager.CalendarPager;
import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Components.Pages.QuotesPageAwareInterface;
import com.example.solbianca.bashim.Extractors.BestQuoteExtractor;
import com.example.solbianca.bashim.Extractors.ExtractorInterface;
import com.example.solbianca.bashim.Extractors.Parser.EmptyPaginationParser;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParser;
import com.example.solbianca.bashim.Extractors.QuoteExtractor;
import com.example.solbianca.bashim.Listeners.EndlessRecyclerViewScrollListener;
import com.example.solbianca.bashim.R;
import com.example.solbianca.bashim.Tasks.BestQuotesPageTask;
import com.example.solbianca.bashim.Tasks.QuotesPageTask;

import java.util.List;

public class BestQuotesFragment extends QuotesFragment implements QuotesPageAwareInterface {

    private String quotesRoute;
    private RecyclerView quoteRecyclerView;
    private QuotesAdapter quotesAdapter;
    private Spinner yearsSpinner;
    private Spinner monthsSpinner;

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

        this.showCalendarPager(view, this.quoteRecyclerView);

        this.loadFirstPage();
        super.onActivityCreated(savedInstanceState);
    }

    public void showCalendarPager(View view, RecyclerView recyclerView) {
        CalendarPager pager = new CalendarPager();
        Button today = view.findViewById(R.id.today_button);
        this.yearsSpinner = view.findViewById(R.id.years_spinner);
        this.monthsSpinner = view.findViewById(R.id.month_spinner);
        ArrayAdapter<Integer> yearsSpinnerAdapter = new ArrayAdapter<Integer>(
                this.getActivity(),
                android.R.layout.simple_spinner_item,
                pager.getYears()
        );
        ArrayAdapter<Integer> monthSpinnerAdapter = new ArrayAdapter<Integer>(
                this.getActivity(),
                android.R.layout.simple_spinner_item,
                pager.getMonths()
        );
        this.yearsSpinner.setAdapter(yearsSpinnerAdapter);
        this.monthsSpinner.setAdapter(monthSpinnerAdapter);

        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.calendar_pager);
        layout.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams parameters = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
        parameters.setMargins(parameters.leftMargin, 140, parameters.rightMargin, parameters.bottomMargin); // left, top, right, bottom
        recyclerView.setLayoutParams(parameters);

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPage();
            }
        });

        this.yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedYear = parent.getSelectedItem().toString();
                if (selectedYear.equals("")) {
                    return;
                }

                loadPage(Integer.parseInt(selectedYear));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.monthsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMonth = parent.getSelectedItem().toString();
                if (selectedMonth.equals("")) {
                    return;
                }

                String selectedYear = yearsSpinner.getSelectedItem().toString();

                if (selectedYear.equals("")) {
                    selectedYear = CalendarPager.getCurrentYear().toString();
                }
                loadPage(Integer.parseInt(selectedYear), Integer.parseInt(selectedMonth));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void loadPage() {
        this.quotesAdapter.clearQuotes();
        this.quotesAdapter.showLoading(true);
        QuotesPageAwareInterface fragment = (QuotesPageAwareInterface) this;
        BestQuotesPageTask task = new BestQuotesPageTask(fragment, this.quotesAdapter, this.createExtractor());
        task.execute();
    }

    public void loadPage(Integer year) {
        this.quotesAdapter.clearQuotes();
        this.quotesAdapter.showLoading(true);
        QuotesPageAwareInterface fragment = (QuotesPageAwareInterface) this;
        BestQuotesPageTask task = new BestQuotesPageTask(fragment, this.quotesAdapter, this.createExtractor());
        task.execute(year);
    }

    public void loadPage(Integer year, Integer month) {
        this.quotesAdapter.clearQuotes();
        this.quotesAdapter.showLoading(true);
        QuotesPageAwareInterface fragment = (QuotesPageAwareInterface) this;
        BestQuotesPageTask task = new BestQuotesPageTask(fragment, this.quotesAdapter, this.createExtractor());
        task.execute(year, month);
    }

    private BestQuoteExtractor createExtractor() {
        return new BestQuoteExtractor(new QuotesParser());
    }

    public void loadFirstPage() {
        this.loadPage();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
