package com.example.solbianca.bashim.Extractors.Parser;

import com.example.solbianca.bashim.Components.Pager.PagerInterface;
import com.example.solbianca.bashim.Components.Pager.QuotesPager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PaginationParser implements PaginationParserInterface {

    private QuotesPager pager;

    public PaginationParser() {
    }

    public PaginationParser parse(String page) {
        Document document = Jsoup.parse(page);
        Elements pager = document.select("div.pager").select("span.current").select("input");
        Integer min = Integer.parseInt(pager.attr("min"));
        Integer max = Integer.parseInt(pager.attr("max"));
        Integer current = Integer.parseInt(pager.attr("value"));
        this.pager = new QuotesPager(min, max, current);
        return this;
    }

    public PagerInterface getPager() {
        return pager;
    }
}
