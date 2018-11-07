package com.example.solbianca.bashim.Extractors;

import com.example.solbianca.bashim.Components.Pager.EmptyPager;
import com.example.solbianca.bashim.Components.Pager.PagerInterface;
import com.example.solbianca.bashim.Components.Pages.PageInterface;
import com.example.solbianca.bashim.Components.Pages.QuotesPage;
import com.example.solbianca.bashim.Entities.Quote;
import com.example.solbianca.bashim.Extractors.Parser.PaginationParserInterface;
import com.example.solbianca.bashim.Extractors.Parser.QuotesParserInterface;
import com.example.solbianca.bashim.Services.BashImApi;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BestQuoteExtractor implements ExtractorInterface {

    private QuotesParserInterface quotesParser;
    private PaginationParserInterface paginationParser;

    public BestQuoteExtractor(QuotesParserInterface quotesParser) {
        this.quotesParser = quotesParser;
        this.paginationParser = paginationParser;
    }

    public PageInterface extract() {
        String url = BashImApi.HOST + "/best/";
        ArrayList<Quote> quotes = this.extractQuotes(url);

        return new QuotesPage(quotes, new EmptyPager());
    }

    public PageInterface extract(Integer year) {
        String url = BashImApi.HOST + "/bestyear/" + year.toString() + "/";
        ArrayList<Quote> quotes = this.extractQuotes(url);

        return new QuotesPage(quotes, new EmptyPager());
    }

    public PageInterface extract(Integer year, Integer month) {
        String url = BashImApi.HOST + "/bestmonth/" + year.toString() + "/" + month.toString() + "/";
        ArrayList<Quote> quotes = this.extractQuotes(url);

        return new QuotesPage(quotes, new EmptyPager());
    }

    private ArrayList<Quote> extractQuotes(String url) {
        String page = loadPage(url);
        if (page == null) {
            return null;
        }
        return this.quotesParser.parse(page).getQuotes();
    }

    private String loadPage(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).build();
        BashImApi bashIm = retrofit.create(BashImApi.class);
        Call<ResponseBody> call = bashIm.index();

        try {
            Response<ResponseBody> response = call.execute();
            String body = response.body().string();
            return body;
        } catch (IOException e) {
            return null;
        }
    }
}
