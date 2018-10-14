package com.example.solbianca.bashim.Extractors.Parser;

import com.example.solbianca.bashim.Components.Pager.PagerInterface;
import com.example.solbianca.bashim.Components.Pager.WantMorePager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class WantMorePaginationParser implements PaginationParserInterface {

    private PagerInterface pager;

    @Override
    public PaginationParserInterface parse(String page) {
        Document document = Jsoup.parse(page);
        Elements pager = document.select("div.quote.more").select("a");
        String rawNextPage = pager.attr("href");
        Integer nextPage = new Scanner(rawNextPage).useDelimiter("[^0-9]+").nextInt();
        this.pager = new WantMorePager(nextPage);
        return this;
    }

    public PagerInterface getPager() {
        return pager;
    }
}
