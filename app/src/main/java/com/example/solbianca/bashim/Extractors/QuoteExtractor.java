package com.example.solbianca.bashim.Extractors;

import android.util.Log;

import com.example.solbianca.bashim.Components.QuotesPager;
import com.example.solbianca.bashim.Entities.Quote;
import com.example.solbianca.bashim.Services.BashImApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuoteExtractor {

    private String route;

    public QuoteExtractor(String route) {
        this.route = route;
    }

    public ArrayList<Quote> extract(Integer pageNum) {
        String url = createUrl(pageNum);
        String page = loadPage(url);
        if (page == null) {
            return null;
        }
        ArrayList<Quote> quotes = parsePage(page);

        return quotes;
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

    private ArrayList<Quote> parsePage(String page) {
        Document document = Jsoup.parse(page);
        Elements parsedQuotes = document.select("div.quote");

        if (parsedQuotes.isEmpty()) {
            return null;
        }

        ArrayList<Quote> quotes = new ArrayList<Quote>();
        for (Element parsedQuote : parsedQuotes) {
            String parsedId = parsedQuote.select("a.id").html();
            if (parsedId.isEmpty()) {
                continue;
            }

            Quote quote = new Quote();
            quote.setId(Integer.parseInt(parsedId.replaceAll("[\\D]", "")));
            quote.setRating(parsedQuote.select("span.rating").html());
            quote.setDate(parsedQuote.select("span.date").html());
            quote.setText(parsedQuote.select("div.text").html());
            quotes.add(quote);
        }
        return quotes;
    }

    public QuotesPager pagination() {
        String url = BashImApi.HOST + "/" + this.route + "/";

        String page = loadPage(url);
        if (page == null) {
            return null;
        }

        Document document = Jsoup.parse(page);
        Elements pager = document.select("div.pager").select("span.current").select("input");
        Integer min = Integer.parseInt(pager.attr("min"));
        Integer max = Integer.parseInt(pager.attr("max"));
        Integer currentNew = Integer.parseInt(pager.attr("value"));
        QuotesPager quotesPager = new QuotesPager(min, max, currentNew, 1);
        return quotesPager;
    }
}
