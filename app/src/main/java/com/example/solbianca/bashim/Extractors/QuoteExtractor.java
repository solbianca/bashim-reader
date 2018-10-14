package com.example.solbianca.bashim.Extractors;

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

public class QuoteExtractor implements ExtractorInterface {

    private String route;
    private QuotesParserInterface quotesParser;
    private PaginationParserInterface paginationParser;

    public QuoteExtractor(QuotesParserInterface quotesParser, PaginationParserInterface paginationParser, String route) {
        this.route = route;
        this.quotesParser = quotesParser;
        this.paginationParser = paginationParser;
    }

    public void setQuotesParser(QuotesParserInterface quotesParser) {
        this.quotesParser = quotesParser;
    }

    public void setPaginationParser(PaginationParserInterface paginationParser) {
        this.paginationParser = paginationParser;
    }

    public PageInterface extract(Integer pageNum) {
        ArrayList<Quote> quotes = this.extractQuotes(pageNum);
        PagerInterface pager = this.extractPagination();

        return new QuotesPage(quotes, pager);
    }

    public ArrayList<Quote> extractQuotes(Integer pageNum) {
        String url = createUrl(pageNum);
        String page = loadPage(url);
        if (page == null) {
            return null;
        }
        return this.quotesParser.parse(page).getQuotes();
    }

    public PagerInterface extractPagination() {
        String url = BashImApi.HOST + "/" + this.route + "/";

        String page = loadPage(url);
        if (page == null) {
            return null;
        }

        return this.paginationParser.parse(page).getPager();
    }

    private String createUrl(Integer page) {
        String host = BashImApi.HOST + "/" + this.route + "/";

        if (this.route.equals(BashImApi.QUOTES_NEW)) {
            if (page == 0) {
                return host;
            } else {
                return host + page + "/";
            }
        }

        return host;
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
