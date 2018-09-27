package com.example.solbianca.bashim.Repositories;

import com.example.solbianca.bashim.Entities.Quote;
import com.example.solbianca.bashim.Services.BashImApi;

import java.util.ArrayList;

public class QuotesRepository {

//    private String route;
//
//    public QuotesRepository(String baseRoute) {
//        route = baseRoute;
//    }
//
//    public ArrayList<Quote> extract(Integer pageNum) {
//        String url = createUrl(pageNum);
//        String page = loadPage(url);
//        if (page == null) {
//            return null;
//        }
//        ArrayList<Quote> quotes = parsePage(page);
//
//        return quotes;
//    }
//
//    private String createUrl(Integer page) {
//        String host = BashImApi.HOST + "/" + route + "/";
//
//        if (route.equals(BashImApi.QUOTES_NEW)) {
//            if (page == 0) {
//                return host;
//            } else {
//                return host + page + "/";
//            }
//        }
//
//        return host;
//    }
//

}
